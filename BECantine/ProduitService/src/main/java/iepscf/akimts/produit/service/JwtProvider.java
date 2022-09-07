package iepscf.akimts.produit.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import iepscf.akimts.produit.config.JwtProperties;
import iepscf.akimts.produit.models.dto.AuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JwtProvider {

    private final JwtProperties properties;
    private final UserDetailsService service;
    private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public JwtProvider(JwtProperties properties, UserDetailsService service) {
        this.properties = properties;
        this.service = service;
    }

    public String extractToken(HttpServletRequest request){
        String token = request.getHeader(properties.getHeader());
        if(token != null)
            token = token.replace(properties.getPrefix(), "");
        return token;
    }

    public boolean validateToken(String token){

        Verification verification = JWT.require(Algorithm.HMAC512(properties.getKey()));
        if( properties.getExpires() != null )
            verification.acceptExpiresAt(properties.getExpires());

        try {
            DecodedJWT jwt = verification.build().verify(token);
            UserDetails details = service.loadUserByUsername( jwt.getSubject() );
            return true;
        } 
        catch (AlgorithmMismatchException | SignatureVerificationException ex){
            logger.warn("AUTHENTICATION FAILED - token falsified");
            return false;
        }
        catch (TokenExpiredException ex){
            logger.info("AUTHENTICATION FAILED - token expired");
            return false;
        }
        catch (UsernameNotFoundException ex){
            logger.info("AUTHENTICATION FAILED - user doesn't exist anymore");
            return false;
        }
        catch (Exception ex){
            logger.error("AUTHENTICATION FAILED - " + ex.getMessage(), ex);
            return false;
        }

    }

    public Authentication generateAuthentication(String token){
        UserDetails user = service.loadUserByUsername( JWT.decode(token).getSubject() );
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
    }

    public LocalDateTime generateExpiration(){
        return LocalDateTime.now().plus(properties.getExpires(), ChronoUnit.SECONDS);
    }

    public String generateToken(String username, List<String> roles){
        return JWT.create()
                .withExpiresAt(Instant.ofEpochMilli(System.currentTimeMillis() + properties.getExpires()))
                .withSubject(username)
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512(properties.getKey()));
    }

    public AuthDTO generateDTO(String username, List<String> roles ){
        return AuthDTO.builder()
                .login( username )
                .roles( roles )
                .expiresAt( generateExpiration() )
                .token( "Bearer " + generateToken(username, roles) )
                .build();
    }
}

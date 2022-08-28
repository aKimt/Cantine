package iepscf.akimts.produit.controller;

import iepscf.akimts.produit.models.dto.AuthDTO;
import iepscf.akimts.produit.models.form.LoginForm;
import iepscf.akimts.produit.models.form.RegisterForm;
import iepscf.akimts.produit.service.JwtProvider;
import iepscf.akimts.produit.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtProvider provider;
    private final UserService userService;
    private final AuthenticationManager manager;

    public AuthController(JwtProvider provider, UserService userService, AuthenticationManager manager) {
        this.provider = provider;
        this.userService = userService;
        this.manager = manager;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public AuthDTO login(@Valid @RequestBody LoginForm form){
        Authentication auth = manager.authenticate( new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
        return provider.generateDTO(
                auth.getName(),
                auth.getAuthorities().stream().map( GrantedAuthority::getAuthority ).toList()
        );
    }

    @PostMapping("/register")
    public AuthDTO register(@Valid @RequestBody RegisterForm form){
        return userService.register(form);
    }
}

package iepscf.akimts.produit.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// l'exemple en DB n'est pas encodé
// Dans le cas contraire, le PasswordEncoder conseillé aurait été utilisé,
// c'est à dire, un bean BCryptPasswordEncoder aurait été créé
@Component
public class BadPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}

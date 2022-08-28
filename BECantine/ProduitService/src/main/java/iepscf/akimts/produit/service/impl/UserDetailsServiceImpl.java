package iepscf.akimts.produit.service.impl;

import iepscf.akimts.produit.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        iepscf.akimts.data.entity.User user = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("no user with this login"));
        return new User(
                user.getLogin(),
                user.getPwd(),
                user.getRoles().stream()
                        .map((role) -> new SimpleGrantedAuthority("ROLE_"+role))
                        .toList()
        );
    }
}

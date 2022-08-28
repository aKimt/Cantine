package iepscf.akimts.produit.service.impl;

import iepscf.akimts.data.entity.User;
import iepscf.akimts.produit.models.dto.AuthDTO;
import iepscf.akimts.produit.models.form.RegisterForm;
import iepscf.akimts.produit.repository.UserRepository;
import iepscf.akimts.produit.service.JwtProvider;
import iepscf.akimts.produit.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository repository, JwtProvider jwtProvider) {
        this.repository = repository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public AuthDTO register(RegisterForm form) {
        if( repository.findByLogin(form.getLogin()).isPresent() )
            throw new RuntimeException();

        User user = repository.save( form.toEntity() );

        return jwtProvider.generateDTO(
                user.getLogin(),
                user.getRoles().stream().toList()
        );
    }
}

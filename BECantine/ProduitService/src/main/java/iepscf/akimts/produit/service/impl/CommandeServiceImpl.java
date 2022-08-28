package iepscf.akimts.produit.service.impl;

import iepscf.akimts.data.entity.Commande;
import iepscf.akimts.data.entity.User;
import iepscf.akimts.produit.mapper.CommandeMapper;
import iepscf.akimts.produit.models.form.CommandeForm;
import iepscf.akimts.produit.repository.CommandeRepository;
import iepscf.akimts.produit.repository.UserRepository;
import iepscf.akimts.produit.service.CommandeService;
import org.springframework.stereotype.Service;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository repository;
    private final UserRepository userRepository;

    public CommandeServiceImpl(CommandeRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void createOrder(String username, CommandeForm form) {

        Commande cmd = CommandeMapper.formToEntity(form);

        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalArgumentException("no-existant user"));

        cmd.setUser(user.getLogin());
        cmd.setBur(user.getBureau());

        repository.save(cmd);

    }
}

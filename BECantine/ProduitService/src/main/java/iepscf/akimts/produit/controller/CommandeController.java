package iepscf.akimts.produit.controller;

import iepscf.akimts.produit.models.form.CommandeForm;
import iepscf.akimts.produit.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/commande")
public class CommandeController {

    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/confirmer")
    public void confirmer(Authentication authentication, @Valid @RequestBody CommandeForm form){
        service.createOrder(authentication.getName(), form);
    }

}

package iepscf.akimts.produit.controller;

import iepscf.akimts.produit.exceptions.NotFoundException;
import iepscf.akimts.produit.models.dto.*;
import iepscf.akimts.produit.models.form.BoissonForm;
import iepscf.akimts.produit.service.ProduitService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @PostMapping
    public BoissonForm test(@Valid @RequestBody BoissonForm form){
        return form;
    }

    @GetMapping("/all")
    public ProduitExhaustiveListDTO getAll() throws NoSuchMethodException {
        ProduitExhaustiveListDTO list = service.getAll();

        list.getBoissons().forEach((boisson -> boisson.add(
                linkTo(ProduitController.class).slash("/boisson?nom="+ boisson.getNom()).withSelfRel()
        )));

        list.getPlatChauds().forEach((plat -> plat.add(
                linkTo(ProduitController.class).slash("/plat?nom="+ plat.getNom()).withSelfRel()
        )));

        list.getSandwiches().forEach((sandwich -> sandwich.add(
                linkTo(ProduitController.class).slash("/sandwich?nom="+ sandwich.getNom()).withSelfRel()
        )));

        return list.add(
                linkTo(ProduitController.class.getMethod("getAll")).withSelfRel()
        );
    }

    @GetMapping(value = "/sandwich")
    public CollectionModel<SandwichDTO> getAllSandwiches(){
        return CollectionModel.of(service.getAllSandwiches()).add(
                linkTo(ProduitController.class).slash("/sandwich").withSelfRel()
        );
    }

    @GetMapping(value = "/boisson")
    public CollectionModel<BoissonDTO> getAllBoissons(){
        return CollectionModel.of(service.getAllBoissons()).add(
                linkTo(ProduitController.class).slash("/boisson").withSelfRel()
        );
    }

    @GetMapping(value = "/plat")
    public CollectionModel<PlatChaudDTO> getAllPlats(){
        return CollectionModel.of(service.getAllPlats()).add(
                linkTo(ProduitController.class).slash("/plat").withSelfRel()
        );
    }

    @GetMapping(value = "/{type}", params = "nom")
    public ProduitDTO getByNom(@PathVariable String type, @RequestParam String nom){
        return switch (type) {
            case "boisson" -> service.getBoisson(nom).add(
                        linkTo(ProduitController.class).slash("/boisson?nom="+ nom).withSelfRel()
                    );
            case "plat" -> service.getPlatChaud(nom).add(
                    linkTo(ProduitController.class).slash("/plat?nom="+ nom).withSelfRel()
            );
            case "sandwich" -> service.getSandwich(nom).add(
                    linkTo(ProduitController.class).slash("/sandwich?nom="+ nom).withSelfRel()
            );
            default -> throw new NotFoundException();
        };
    }

    @GetMapping("/supplements")
    public SupplementList getSupplements(){
        return service.getSupplement().add(
                linkTo(ProduitController.class).slash("/supplements").withSelfRel(),
                linkTo(ProduitController.class).slash("/sandwich").withRel("sandwiches")
        );
    }
}

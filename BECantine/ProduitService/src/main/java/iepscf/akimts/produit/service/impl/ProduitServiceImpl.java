package iepscf.akimts.produit.service.impl;

import iepscf.akimts.data.entity.Sandwich;
import iepscf.akimts.produit.exceptions.NoRessourseFoundException;
import iepscf.akimts.produit.models.dto.*;
import iepscf.akimts.produit.repository.BoissonRepository;
import iepscf.akimts.produit.repository.PlatChaudRepository;
import iepscf.akimts.produit.repository.SandwichRepository;
import iepscf.akimts.produit.service.ProduitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final BoissonRepository boissonRep;
    private final PlatChaudRepository platRep;
    private final SandwichRepository sandwichRep;

    public ProduitServiceImpl(BoissonRepository boissonRep, PlatChaudRepository platRep, SandwichRepository sandwichRep) {
        this.boissonRep = boissonRep;
        this.platRep = platRep;
        this.sandwichRep = sandwichRep;
    }

    @Override
    public ProduitExhaustiveListDTO getAll() {
        return ProduitExhaustiveListDTO.of(
                boissonRep.findAll(),
                platRep.findAll(),
                sandwichRep.findAll().stream()
                        .filter(s-> !Objects.equals(s.getNom(), "Suppléments"))
                        .collect(Collectors.toSet())
        );
    }

    public Set<SandwichDTO> getAllSandwiches(){
        return sandwichRep.findAll().stream()
                .filter(s-> !Objects.equals(s.getNom(), "Suppléments"))
                .map(SandwichDTO::of)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PlatChaudDTO> getAllPlats() {
        return platRep.findAll().stream()
                .map(PlatChaudDTO::of)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BoissonDTO> getAllBoissons() {
       return boissonRep.findAll().stream()
                .map(BoissonDTO::of)
                .collect(Collectors.toSet());
    }

    @Override
    public BoissonDTO getBoisson(String nom) {
        return boissonRep.findByNom(nom)
                .map(BoissonDTO::of)
                .orElseThrow(() -> new NoRessourseFoundException("nom", nom));
    }

    @Override
    public PlatChaudDTO getPlatChaud(String nom) {
        return platRep.findByNom(nom)
                .map(PlatChaudDTO::of)
                .orElseThrow(() -> new NoRessourseFoundException("nom", nom));
    }

    @Override
    public SandwichDTO getSandwich(String nom) {
        return sandwichRep.findByNom(nom)
                .map(SandwichDTO::of)
                .orElseThrow(() -> new NoRessourseFoundException("nom", nom));
    }

    @Override
    public SupplementList getSupplement(){
        return new SupplementList(
                sandwichRep.findByNom("Suppléments")
                    .orElseThrow(() -> new NoRessourseFoundException("nom", "Suppléments"))
                    .getComposition().stream()
                    .map(ComposantDTO::of)
                    .collect(Collectors.toSet())
        );
    }
}

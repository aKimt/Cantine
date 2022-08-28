package iepscf.akimts.produit.service;

import iepscf.akimts.produit.models.dto.*;

import java.util.Set;

public interface ProduitService {

    ProduitExhaustiveListDTO getAll();

    Set<SandwichDTO> getAllSandwiches();
    Set<PlatChaudDTO> getAllPlats();
    Set<BoissonDTO> getAllBoissons();

    BoissonDTO getBoisson(String nom);
    PlatChaudDTO getPlatChaud(String nom);
    SandwichDTO getSandwich(String nom);
    SupplementList getSupplement();



}

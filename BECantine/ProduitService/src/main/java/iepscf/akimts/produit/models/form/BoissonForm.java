package iepscf.akimts.produit.models.form;

import lombok.Data;

import java.util.List;

@Data
public class BoissonForm {

    private String nom;

    private double prix;

    private List<Integer> nombres;

}

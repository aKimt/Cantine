package iepscf.akimts.data.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Composant{
    private String ingredient;
    private double qte;
    private String unite;
}
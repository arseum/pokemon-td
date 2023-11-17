package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeRalentissement implements ForgeEffetImpact {

    private final Tour tireur;
    private int tauxDeReductionVitesse;
    private Seconde dureeDeRalentissement;

    public ForgeRalentissement(Tour tireur, int tauxDeReductionVitesse, Seconde dureeDeRalentissement) {
        this.tireur = tireur;
        this.tauxDeReductionVitesse = tauxDeReductionVitesse;
        this.dureeDeRalentissement = dureeDeRalentissement;
    }

    @Override
    public EffetImpact genereEffect() {
        return new EffetRalentissement(tireur, tauxDeReductionVitesse, dureeDeRalentissement);
    }

}

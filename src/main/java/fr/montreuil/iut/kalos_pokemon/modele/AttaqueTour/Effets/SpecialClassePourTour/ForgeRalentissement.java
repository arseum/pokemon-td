package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeRalentissement implements ForgeEffectImpact {

    private final Tour tireur;
    private int reductionVitesse;

    public ForgeRalentissement(Tour tireur, int reductionVitesse) {
        this.tireur = tireur;
        this.reductionVitesse = reductionVitesse;
    }

    /** GETTER + SETTER */
    public void setReductionVitesse(int reductionVitesse) {
        this.reductionVitesse = reductionVitesse;
    }

    /** methode public */
    @Override
    public EffetImpact genereEffect() {
        return new EffetRalentissement(tireur,reductionVitesse);
    }

}

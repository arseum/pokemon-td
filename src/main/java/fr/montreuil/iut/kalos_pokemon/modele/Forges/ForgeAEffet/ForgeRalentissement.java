package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetRalentissementDeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeRalentissement implements ForgeEffetImpact {

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
        return new EffetRalentissementDeZone(tireur,reductionVitesse);
    }

}

package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeEffetNull implements ForgeEffetImpact {

    private Tour myTour;

    public ForgeEffetNull(Tour myTour) {
        this.myTour = myTour;
    }

    @Override
    public EffetImpact genereEffect() {
        return new EffetNull(myTour);
    }
}

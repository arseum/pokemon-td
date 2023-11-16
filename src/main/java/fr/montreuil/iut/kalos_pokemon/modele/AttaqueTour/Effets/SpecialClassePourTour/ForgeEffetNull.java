package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeEffetNull implements ForgeEffectImpact{

    private Tour myTour;

    public ForgeEffetNull(Tour myTour) {
        this.myTour = myTour;
    }

    @Override
    public EffetImpact genereEffect() {
        return new EffetNull(myTour);
    }
}

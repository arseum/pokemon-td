package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class TourSlow extends TourAvecType implements TourSpe {

    public TourSlow(Tour t){
        super(t);
    }

    @Override
    public void appliqueEffet() {

    }
}

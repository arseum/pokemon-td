package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ModeDattaque;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class ModeAttaque {

    protected Tour myTour;

    public ModeAttaque(Tour myTour) {
        this.myTour = myTour;
    }

    public abstract void attaque();

}

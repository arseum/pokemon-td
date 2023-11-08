package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class TourAvecType extends Tour implements TourSpe {

    protected Tour myTour;

    public TourAvecType(Tour t){
        super(t.getPortee(),t.getDegats(),t.getType(),t.getPrix(),t.getX(),t.getY(),t.getNom(),t.getAttaqueSpeed(),t.getMyCompetence());
        myTour = t;
    }

}

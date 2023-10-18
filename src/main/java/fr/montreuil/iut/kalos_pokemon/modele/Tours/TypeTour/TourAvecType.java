package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

public abstract class TourAvecType extends Tour implements TourSpe {

    protected Tour myTour;

    public TourAvecType(Tour t){
        //todo j'ai penser a remplacer ce truc par une strategie de 'lance projectile'
        super(t.getPortee(),t.getDegats(),t.getType(),t.getPrix(),t.getX(),t.getY(),t.getNom(),t.getAttaqueSpeed(),t.getMyCompetence(),null);
        myTour = t;

    }

    @Override
    public double getCompteurDegats() {
        return myTour.getCompteurDegats() + compteurDegats.get();
    }

    @Override
    public void lanceProjectile(Ennemi cible, ArrayList<EffetImpact> listEffect) {
        myTour.lanceProjectile(cible,listEffect);
    }

    @Override
    public void setGame(Game game) {
        //todo a retirer avec le singleton
        super.setGame(game);
        myTour.setGame(game);
    }
}

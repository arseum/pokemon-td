package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

public abstract class TourAvecType implements ProjectileEffect {

    protected Tour myTour;

    public TourAvecType(Tour t) {
        myTour = t;
    }

    public void lanceProjectile(Ennemi cible, ArrayList<EffetImpact> listEffect) {
        myTour.lanceProjectile(cible,listEffect);
    }
}

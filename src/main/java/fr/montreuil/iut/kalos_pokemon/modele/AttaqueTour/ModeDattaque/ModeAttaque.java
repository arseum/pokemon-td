package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ModeDattaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

public abstract class ModeAttaque {

    protected Tour myTour;
    protected ArrayList<EffetImpact> myEffects;

    public ModeAttaque(Tour myTour) {
        this.myTour = myTour;
        myEffects = new ArrayList<>();
    }

    public ModeAttaque(Tour myTour, ArrayList<EffetImpact> myEffects) {
        this.myTour = myTour;
        this.myEffects = myEffects;
    }

    public void ajouteEffect(EffetImpact e) {
        myEffects.add(e);
    }

    public abstract void attaque();

}

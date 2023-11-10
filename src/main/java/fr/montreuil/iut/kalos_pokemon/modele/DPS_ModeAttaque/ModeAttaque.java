package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class ModeAttaque {
    //todo Notes
    //Peut-être une arrayListe
    protected EffetImpact effetAttaque;
    protected Tour tourCible;

    public ModeAttaque(EffetImpact effetAttaque, Tour tourCible) {
        this.effetAttaque = effetAttaque;
        this.tourCible = tourCible;
    }

    public abstract void attaque();

}

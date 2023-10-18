package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class Slow extends EffetImpact{
    public Slow(Tour t) {
        super(t);
    }

    @Override
    public boolean peutEtreAppliquer(int nbFrameValue) {
        return false;
    }

    @Override
    public boolean finDeVie() {
        return false;
    }
}

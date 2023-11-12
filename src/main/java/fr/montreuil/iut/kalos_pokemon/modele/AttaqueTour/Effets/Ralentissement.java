package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class Ralentissement extends EffetImpact{
    public Ralentissement(Tour t) {
        super(t);
    }

    @Override
    public void initEffetType() {
        this.typeEffet = TypeEffet.Ralentissement;
    }

    @Override
    public boolean peutEtreApplique(int nbFrameValue) {
        return false;
    }

    @Override
    public void appliqueEffet() {

    }

    @Override
    public boolean finDeVie() {
        return false;
    }
}

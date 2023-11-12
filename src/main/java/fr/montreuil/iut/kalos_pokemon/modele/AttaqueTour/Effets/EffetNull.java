package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class EffetNull extends EffetImpact{
    public EffetNull(Tour t) {
        super(t);
    }

    @Override
    public void initEffetType() {
        this.typeEffet = TypeEffet.Null;
    }

    @Override
    public boolean peutEtreApplique(int nbFrameValue) {
        return false;
    }

    @Override
    public void appliqueEffet() {
        //Ne fais rien
    }

    @Override
    public boolean finDeVie() {
        return false;
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
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
    public boolean peutEtreApplique(Ennemi ennemi) {
        return false;
    }

    @Override
    public void appliqueEffet(Ennemi ennemi) {
        //Ne fais rien
    }

    @Override
    public boolean finDeVie(Ennemi ennemi) {
        return false;
    }
}

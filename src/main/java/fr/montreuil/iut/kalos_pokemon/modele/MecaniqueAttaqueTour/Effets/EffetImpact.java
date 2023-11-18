package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/** EffetImpact a pour principale responsabilite de fournir les methodes definisant comment et quand les les effets s'appliquent
 */
public abstract class EffetImpact {
    protected int frameDebutDeVie;
    protected Tour tireur;
    protected TypeEffet typeEffet;

    public EffetImpact(Tour t) {
        this.tireur = t;
        initEffetType();
    }

    public abstract void initEffetType();

    public void initialiserDebutEffet(int frameDebutDeVie) {
        this.frameDebutDeVie = frameDebutDeVie;
    }

    public abstract boolean peutEtreApplique(Ennemi ennemi);

    public abstract void appliqueEffet(Ennemi ennemi);

    public abstract boolean finEffet(Ennemi ennemi);

    //SETTERS, GETTERS & PROPERTIES

    public Tour getTireur() {
        return tireur;
    }

    public TypeEffet getTypeEffet() {
        return typeEffet;
    }

    public abstract int getPuissanceEffet();
}

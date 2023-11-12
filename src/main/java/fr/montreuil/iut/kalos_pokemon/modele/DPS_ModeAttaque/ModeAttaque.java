package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * Une tour ne peut avoir qu'un mode attaque. Ce mode peut être amené à changer
 * Cette classe a pour responsabilité de definir la manière dont la tour va cibler.
 * Elle va donc instancier un projectile qui contient l'effet du mode attaque
 * (C'est la classe projectile qui a pour responsabilité d'appliquer les éventuels dégats et effets)
 */
public abstract class ModeAttaque {
    protected EffetImpact effetAttaque;
    protected Tour tourCible;

    public ModeAttaque(EffetImpact effetAttaque, Tour tourCible) {
        this.effetAttaque = effetAttaque;
        this.tourCible = tourCible;
    }

    public abstract void attaque();
}

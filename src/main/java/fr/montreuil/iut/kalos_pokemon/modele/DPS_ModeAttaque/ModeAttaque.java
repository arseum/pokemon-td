package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

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

    protected Ennemi chercheCible() {
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        for (int i = 0; i < listEnnemi.size(); i++){
            if(this.tourCible.estADistance(listEnnemi.get(i)))
                return listEnnemi.get(i);
        }
        return null;
    }

    protected ArrayList<Ennemi> chercheCibles(){
        ArrayList<Ennemi> listeCibles = new ArrayList<>();
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        for (Ennemi ennemi : listEnnemi) {
            if (this.tourCible.estADistance(ennemi)) {
                listeCibles.add(ennemi);
            }
        }
        return listeCibles;
    }
}

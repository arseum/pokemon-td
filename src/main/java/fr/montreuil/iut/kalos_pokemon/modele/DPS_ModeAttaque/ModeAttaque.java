package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Une tour ne peut avoir qu'un mode attaque. Ce mode peut être amené à changer
 * Cette classe a pour responsabilité de definir la manière dont la tour va cibler.
 * Elle va donc instancier un projectile qui contient l'effet du mode attaque
 * (C'est la classe projectile qui a pour responsabilité d'appliquer les éventuels dégats et effets)
 */
public abstract class ModeAttaque {
    /**
     * un mode d'attaque est forcement lié a une tour (compteur degats...)
     */
    protected Tour tourCible;

    public ModeAttaque(Tour tourCible) {
        this.tourCible = tourCible;
    }

    public abstract void attaque(int degats, ForgeEffectImpact forgeEffet);

    protected Ennemi chercheCible() {
        return chercheCibles().get(0);
//        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();
//
//        for (Ennemi ennemi : listEnnemi) {
//            if (this.tourCible.estADistance(ennemi))
//                return ennemi;
//        }
//        return null;
    }

    protected List<Ennemi> chercheCibles(){
        return Game.getGame().getListEnnemi().stream().map(e -> tourCible.estADistance(e) ? e : null).toList();
//        ArrayList<Ennemi> listeCibles = new ArrayList<>();
//        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();
//
//        for (Ennemi ennemi : listEnnemi) {
//            if (this.tourCible.estADistance(ennemi)) {
//                listeCibles.add(ennemi);
//            }
//        }
//
//        return listeCibles;
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;

public abstract class ModeTireur extends ModeAttaque {
    public ModeTireur(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }

    protected Ennemi chercheCible() {
        Ennemi cible = null;
        int index = 0;
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();
        while (cible == null && index < listEnnemi.size()) {
            if (this.tourCible.estADistance(listEnnemi.get(index)))
                cible = listEnnemi.get(index);
            else
                index++;
        }
        return cible;
    }
}

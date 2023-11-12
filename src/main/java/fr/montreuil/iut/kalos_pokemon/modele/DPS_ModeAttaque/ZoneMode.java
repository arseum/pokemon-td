package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;

public class ZoneMode extends ModeAttaque {
    public ZoneMode(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }

    @Override
    public void attaque() {
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        //Changement mode attaque a tour en attribut (si c'est que distance porte moyen de faire autrement)
        for (Ennemi ennemi : listEnnemi) {
            if (this.tourCible.estADistance(ennemi) && !ennemi.estEffecteParEffet(this.effetAttaque)) {
                Projectile projectile = new Projectile(this.tourCible, ennemi, this.effetAttaque);
                //C'est le projectile qui a pour responsabilite de faire des degats et d'ajouter les effets
                Game.getGame().ajouteProjectile(projectile);
            }
        }
    }
}

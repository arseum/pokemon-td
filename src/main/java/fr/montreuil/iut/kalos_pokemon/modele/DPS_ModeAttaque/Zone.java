package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;

public class Zone extends ModeAttaque {
    public Zone(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }

    @Override
    public void attaque() {
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        //Changement mode attaque a tour en attribut (si c'est que distance porte moyen de faire autrement)
        for (Ennemi e : listEnnemi) {
            if (this.tourCible.estADistance(e)) {
                System.out.println("JATTAQUE TABERNAK");
                e.ajouteEffet(this.effetAttaque);
                //Ici, c'est juste pour voir le "projectile" (Peut être en faire un generique ou bien reprendre deja existant)
                //Game.getGame().ajouteProjectile(new Projectile(this.tourCible, e));

                //todo: doit prendre en compte le type
                //Normalement c'est ajout effet qui enleve les degats?
                //Parce que la c'est un doublon
                //e.diminueHP(this.tourCible.getDegats());
            }
        }
    }
}

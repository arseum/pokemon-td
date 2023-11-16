package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;
//FIXME Revoir projectile (genre il y a des projectile qui applique des effets)
// et des zone qui applique aussi effet?
public class ModeZone extends ModeAttaque {
    public ModeZone(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque() {
        /*
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        for (Ennemi ennemi : listEnnemi) {
            //FIXME on va avoir un pb avoir les sans effets
            //if (this.tourCible.estADistance(ennemi) && !ennemi.estEffecteParEffet(this.effetAttaque)) {
            if (this.tourCible.estADistance(ennemi)) {
                Projectile projectile = new Projectile(this.tourCible, ennemi, this.effetAttaque);
                //C'est le projectile qui a pour responsabilite de faire des degats et d'ajouter les effets
                Game.getGame().ajouteProjectile(projectile);
            }
        }

         */
        for (Ennemi ennemi : chercheCibles()){
            if (this.tourCible.estADistance(ennemi)) {
                /*
                Projectile projectile = new Projectile(this.tourCible, ennemi, this.effetAttaque);
                //C'est le projectile qui a pour responsabilite de faire des degats et d'ajouter les effets
                Game.getGame().ajouteProjectile(projectile);

                 */
                //FIXME ici c'est le meme putain d'objet qui est passé en ref

                ennemi.ajouteEffet(this.tourCible.getMyForgeEffectImpact().genereEffect());
                ennemi.diminueHP(tourCible.getDegats()); //todo calcul type


            }
        }
    }
}

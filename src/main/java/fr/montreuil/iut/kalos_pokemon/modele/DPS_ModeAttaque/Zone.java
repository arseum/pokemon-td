package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;

public class Zone extends ModeAttaque{
    public Zone(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }

    @Override
    public void attaque() {
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        //Changement mode attaque a tour en attribut (si c'est que distance porte moyen de faire autrement)
        for (Ennemi e : listEnnemi) {
            if (this.tourCible.estADistance(e)){
                System.out.println("JATTAQUE TABERNAK");
                e.ajouteEffet(this.effetAttaque);
                //todo: doit prendre en compte le type
                e.diminueHP(this.tourCible.getDegats());
            }
        }
    }
}

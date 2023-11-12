package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Salameche;

import java.util.List;

public class bouleDeFeu extends Projectile {

    private final int rayonExploxion;

    public bouleDeFeu(Salameche salameche, Ennemi ennemi) {
        //super(salameche, ennemi, game);
        //super(salameche, ennemi);
        //FIXME temporaire
        super(null,null,null);
        rayonExploxion = salameche.getRayonExploxion();
    }

    @Override
    protected void explotionTir() {
        //List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        for (int i = listEnnemi.size() - 1 ; i >= 0 ; i--)
            if (Parametres.distance(this,listEnnemi.get(i)) <= rayonExploxion && listEnnemi.get(i).getHp() > 0) {
                listEnnemi.get(i).diminueHP(degatsReel);
                tireur.ajouteDegats(degatsReel);
            }
        //game.remove(this);
        Game.getGame().remove(this);

    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.bouleDeFeu;

import java.util.List;

public class Salameche extends Tour {

    private int rayonExploxion;
    public Salameche(int x, int y) {
        super(90, 30, "feu", Parametres.prixsalameche, x, y, "salameche", 50);
        rayonExploxion = 57;
    }

    @Override
    public void levelUp() {
        this.level.set(level.get()+1);
        rayonExploxion = 75;
    }

    @Override
    public void actif() {

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            if (Parametres.distance(listEnnemi.get(i),this) <= portee.get() ) {
                listEnnemi.get(i).diminueHP(150);
            }
        }

    }

    @Override
    protected void lanceProjectile(Ennemi cible) {
        game.ajouteProjectile(new bouleDeFeu(this,cible,game));
    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
}

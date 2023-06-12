package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.bouleDeFeu;

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
        //todo faire salameche
    }

    @Override
    public void actif() {

    }

    @Override
    protected void lanceProjectile(Ennemi cible) {
        game.ajouteProjectile(new bouleDeFeu(this,cible,game));
    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
}

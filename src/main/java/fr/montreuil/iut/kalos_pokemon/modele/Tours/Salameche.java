package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.bouleDeFeu;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Salameche extends TourConcrete {

    private int rayonExploxion;

    public Salameche(int x, int y) {
        super(98, 50, "feu", Parametres.prixsalameche, x, y, "salameche", 40);
        rayonExploxion = 57;

    }

    @Override
    protected void amelioreStats() {
        rayonExploxion += 10;
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get()-1));
    }

    @Override
    protected void lanceProjectile(Ennemi cible) {
        game.ajouteProjectile(new bouleDeFeu(this,cible,game));
    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
}

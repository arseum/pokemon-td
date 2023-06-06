package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Salameche extends Tour {
    public Salameche(int x, int y) {
        super(48, 2, "feu", Parametres.prixsalameche, x, y, "salameche", 0, 3);
    }

    @Override
    public void levelUp() {
        //todo faire salameche
    }
}

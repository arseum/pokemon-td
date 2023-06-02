package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Salameche extends Tour {
    public Salameche(int x, int y, Game game) {
        super(48, 40, "feu", Parametres.prixsalameche, x, y, "salameche", 0, game);
    }
}

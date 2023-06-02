package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Venalgue extends Tour {

    public Venalgue(int x, int y, Game game) {
        super(128, 20, "neutre", Parametres.prixvenalgue, x, y, "venalgue", 0, game);
    }
}
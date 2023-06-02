package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Magneti extends Tour {
    public Magneti(int x, int y, Game game) {
        super(64, 0, "neutre", Parametres.prixmagneti, x, y, "magneti", 4, game);
    }
}

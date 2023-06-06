package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Grenousse extends Tour {
    public Grenousse(int x, int y) {
        super(160, 40, "eau", Parametres.prixgrenousse, x, y, "grenousse", 6, 90);
    }

    @Override
    public void levelUp() {
        this.degats = 80;
        this.attaqueSpeed = 75;
    }
}

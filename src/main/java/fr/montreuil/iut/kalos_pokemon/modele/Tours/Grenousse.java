package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Grenousse extends Tour {
    public Grenousse(int x, int y) {
        super(160, 40, "eau", Parametres.prixgrenousse, x, y, "grenousse", 90);
    }

    @Override
    protected void amelioreStats() {
        this.degats += 40;
        this.attaqueSpeed *= 0.8;
    }
}

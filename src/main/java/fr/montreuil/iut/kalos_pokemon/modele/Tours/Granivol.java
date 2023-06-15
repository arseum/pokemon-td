package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Granivol extends Tour {

    public Granivol(int x, int y) {
        super(160, 3, "plante", Parametres.prixgranivol, x, y, "granivol", 6);
    }

    @Override
    public void levelUp() {

        super.levelUp();

        if (level.get() == 3)
            portee.set(180);
        else {
            attaqueSpeed = 5;
            degats = 4;
        }
    }

    @Override
    public void actif() {

    }
}

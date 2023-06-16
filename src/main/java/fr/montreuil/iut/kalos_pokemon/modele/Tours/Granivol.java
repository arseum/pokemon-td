package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Granivol extends Tour {

    public Granivol(int x, int y) {
        super(160, 4, "plante", Parametres.prixgranivol, x, y, "granivol", 6);
    }

    @Override
    protected void amelioreStats() {
        if (level.get() == Parametres.niveauEvolutionTour) {
            portee.set(180);
        }
        else {
            attaqueSpeed = 5;
        }
        degats += 1;
    }
}

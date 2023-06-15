package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Poussifeu extends Tour {
    public Poussifeu(int x, int y) {
        super(96, 20, "feu", Parametres.prixpoussifeu, x, y, "poussifeu", 60);
    }


    @Override
    public void levelUp() {

        super.levelUp();

        this.degats *= 1.5;
        this.attaqueSpeed -= 5;

    }

    @Override
    public void actif() {

    }
}

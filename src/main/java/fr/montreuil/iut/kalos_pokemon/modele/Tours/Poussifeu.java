package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Poussifeu extends Tour {
    public Poussifeu(int x, int y) {
        super(96, 20, "feu", Parametres.prixpoussifeu, x, y, "poussifeu", 6, 60);
    }


    @Override
    public void levelUp() {
        this.level.set(level.get()+1);

        this.degats = 30;
        this.attaqueSpeed = 55;
    }

    @Override
    public void actif() {

    }
}

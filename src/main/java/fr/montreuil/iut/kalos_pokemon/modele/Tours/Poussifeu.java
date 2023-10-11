package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;

public class Poussifeu extends TourConcrete {
    public Poussifeu(int x, int y) {
        super(100, 30, "feu", Parametres.prixpoussifeu, x, y, "poussifeu", 50);
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.5;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}

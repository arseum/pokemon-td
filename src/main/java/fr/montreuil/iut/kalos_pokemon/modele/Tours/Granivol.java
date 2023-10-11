package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;

public class Granivol extends TourConcrete {

    public Granivol(int x, int y) {
        super(160, 5, "plante", Parametres.prixgranivol, x, y, "granivol", 6);
    }

    @Override
    public void amelioreStats() {
        attaqueSpeed = 5;
        degats += 1;
    }

    @Override
    public void evolution() {
        super.evolution();
        portee.set(180);
    }
}

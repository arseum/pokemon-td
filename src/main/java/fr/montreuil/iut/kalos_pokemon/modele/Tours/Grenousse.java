package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class Grenousse extends Tour {
    public Grenousse(int x, int y) {
        super(160, 70, "eau", Parametres.prixgrenousse, x, y, "grenousse", 90, new NullActif());
    }

    @Override
    public void amelioreStats() {
        this.degats += 25 * (level.get()-1);
        this.attaqueSpeed *= 0.8;
        portee.set(portee.get()+5*level.get());

    }
}

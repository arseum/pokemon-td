package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class Poussifeu extends Tour {
    public Poussifeu(int x, int y) {
        super(100, 30, Pokemon.poussifeu.getType(), Pokemon.poussifeu.getPrix(), x, y, Pokemon.poussifeu.name(), 50, new NullActif());
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.5;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}

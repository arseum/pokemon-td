package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.Parametres;


public class Nidoran extends Tour {

    public Nidoran(int x, int y) {
        super(115, 3, Pokemon.nidoran.getType(), Pokemon.nidoran.getPrix(), x, y, Pokemon.nidoran.name(), 30, null);
    }

    @Override
    public void amelioreStats() {
        this.degats += 4;
        if (level.get() == Parametres.niveauEvolutionTour)
            portee.set(portee.get()+10);
    }


}

package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;


public class Nidoran extends Tour {

    public Nidoran(int x, int y) {
        super(115, 3, PokemonEnum.nidoran.getType(), PokemonEnum.nidoran.getPrix(), x, y, PokemonEnum.nidoran.name(), 30, null);
    }

    @Override
    public void amelioreStats() {
        this.degats += 4;
        if (level.get() == Parametres.niveauEvolutionTour)
            portee.set(portee.get()+10);
    }


}

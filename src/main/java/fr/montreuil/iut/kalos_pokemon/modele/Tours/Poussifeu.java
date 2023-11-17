package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.CompetenceNull;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;

public class Poussifeu extends Tour {
    public Poussifeu(int x, int y) {
        super(100, 30, PokemonEnum.poussifeu.getType(), PokemonEnum.poussifeu.getPrix(), x, y, PokemonEnum.poussifeu.name(), 50, new CompetenceNull());
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.5;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}

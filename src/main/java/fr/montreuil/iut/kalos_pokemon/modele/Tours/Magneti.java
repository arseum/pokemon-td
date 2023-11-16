package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.ImmobilisationZone;

public class Magneti extends Tour {

    public Magneti(int x, int y) {
        super(90, 0, PokemonEnum.magneti.getType(),
                PokemonEnum.magneti.getPrix(), x, y, PokemonEnum.magneti.name(),
                100,null);

        setMyCompetence(new ImmobilisationZone(this,90));

    }


    @Override
    public void amelioreStats() {
        portee.set(portee.get() + (6*level.get()));
        if(level.get() == Parametres.niveauEvolutionTour) {
            //valeurSlow = 2;
        }
    }

}

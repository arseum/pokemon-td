package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;

public class Salameche extends Tour {

    public Salameche(int x, int y) {
        super(300, 50, PokemonEnum.salameche.getType(), PokemonEnum.salameche.getPrix(),
                x, y, PokemonEnum.salameche.name(), 40, null);

        //todo a faire
        //setMyCompetence(new ExplosionAutourTour(this,150,new ForgeEffetNull(this)));
        setModeAttaque(new ModeCiblageAleatoire(this));
        setMyForgeAttaque(new ForgeProjectileExplosif(57));
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
//        rayonExploxion += 10;
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get()-1));
    }
}

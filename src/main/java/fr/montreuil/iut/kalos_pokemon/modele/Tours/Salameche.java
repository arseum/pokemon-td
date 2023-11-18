package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;

public class Salameche extends Tour {

    public Salameche(int x, int y) {
        super(300, 60, PokemonEnum.salameche.getType(), PokemonEnum.salameche.getPrix(),
                x, y, PokemonEnum.salameche.name(), 50, null);
        //setMyCompetence(new ExplosionAutourTour(this,150,new ForgeEffetNull(this)));
        setModeAttaque(new ModeCiblageAleatoire(this));
        setMyForgeAttaque(new ForgeProjectileExplosif(30));
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get() - 1));
        attaqueSpeed += 10;

        if (level.get() == Parametres.niveauEvolutionTour)
            setMyForgeAttaque(new ForgeProjectileExplosif(65));
        else
            setMyForgeAttaque(new ForgeProjectileExplosif(45));
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeBouleDeFeu;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.ExplosionAutourTour;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.bouleDeFeu;

public class Salameche extends Tour {

    public Salameche(int x, int y) {
        super(300, 50, PokemonEnum.salameche.getType(), PokemonEnum.salameche.getPrix(),
                x, y, PokemonEnum.salameche.name(), 40, null);

        setMyCompetence(new ExplosionAutourTour(this,150,new ForgeEffetNull(this)));
        setModeAttaque(new ModeTirAleatoire(this));
        setMyForgeAttaque(new ForgeBouleDeFeu(57));
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
//        rayonExploxion += 10;
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get()-1));
    }
}

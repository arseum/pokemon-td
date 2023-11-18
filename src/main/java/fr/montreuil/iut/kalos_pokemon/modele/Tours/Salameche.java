package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.CompetenceExplosionZone;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;

public class Salameche extends Tour {

    public Salameche(int x, int y) {
        super(100, 60, PokemonEnum.salameche.getType(), PokemonEnum.salameche.getPrix(),
                x, y, PokemonEnum.salameche.name(), 50, new NullActif());
        setModeCiblage(new ModeCiblageAleatoire(this));
        setMyForgeEntiteAttaque(new ForgeProjectileSimple());
        setMyForgeEffectImpact(new ForgeEffetNull(this));
        setMyCompetence(new CompetenceExplosionZone(this, new Seconde(10), 40));
    }

    @Override
    public void amelioreStats() {
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get() - 1));
        attaqueSpeed += 10;
    }
}

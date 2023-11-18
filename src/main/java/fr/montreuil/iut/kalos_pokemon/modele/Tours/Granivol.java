package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblagePrioritaire;

public class Granivol extends Tour {

    public Granivol(int x, int y) {
        super(160, 5, PokemonEnum.granivol.getType(),
                PokemonEnum.granivol.getPrix(), x, y,
                PokemonEnum.granivol.name(), 6, new NullActif());

        setModeCiblage(new ModeCiblagePrioritaire(this));
        setMyForgeEntiteAttaque(new ForgeProjectileSimple());
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
        if (level.get() == Parametres.niveauEvolutionTour) {
            portee.set(180);
        } else {
            attaqueSpeed = 5;
        }
        degats += 1;
    }
}

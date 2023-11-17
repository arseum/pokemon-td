package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblagePrioritaire;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;

public class NewGranivol extends Tour{
    public NewGranivol(int x, int y) {

        super(160, 1, PokemonEnum.granivol.getType(),
                PokemonEnum.granivol.getPrix(), x, y, PokemonEnum.granivol.name(),
                6, new NullActif());
        ModeDeCiblage modeDeCiblage = new ModeCiblagePrioritaire( this);
        setModeAttaque(modeDeCiblage);
        setMyForgeEffectImpact(new ForgeEffetNull(this));
        setMyForgeAttaque(new ForgeProjectileSimple());
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

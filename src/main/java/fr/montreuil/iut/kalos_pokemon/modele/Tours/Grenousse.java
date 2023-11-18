package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblagePrioritaire;

public class Grenousse extends Tour {
    public Grenousse(int x, int y) {
        //super(160, 70, PokemonEnum.grenousse.getType(), PokemonEnum.grenousse.getPrix(), x, y, PokemonEnum.grenousse.name(), 90, new NullActif());
        super(160, 40,
                PokemonEnum.grenousse.getType(),
                PokemonEnum.grenousse.getPrix(), x, y,
                PokemonEnum.grenousse.name(), 90, new NullActif());


        setModeCiblage(new ModeCiblageAleatoire(this));
        setMyForgeEntiteAttaque(new ForgeProjectileSimple());
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
        /*
        this.degats += 25 * (level.get()-1);
        this.attaqueSpeed *= 0.8;
        portee.set(portee.get()+5*level.get());

         */
        this.degats += 25 * (level.get()-1);
        this.attaqueSpeed *= 0.8;
        portee.set(portee.get()+5*level.get());
        if (level.get() == Parametres.niveauEvolutionTour)
            setModeCiblage(new ModeCiblagePrioritaire(this));
    }
}

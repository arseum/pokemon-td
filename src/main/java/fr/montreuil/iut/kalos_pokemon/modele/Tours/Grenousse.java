package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblagePrioritaire;

public class Grenousse extends Tour {
    public Grenousse(int x, int y) {
        super(160, 70,
                PokemonEnum.grenousse.getType(),
                PokemonEnum.grenousse.getPrix(), x, y,
                PokemonEnum.grenousse.name(), 90, new NullActif());


        setModeAttaque(new ModeCiblagePrioritaire(this));
        setMyForgeAttaque(new ForgeProjectileSimple());
        setMyForgeEffectImpact(new ForgeEffetNull(this));
    }

    @Override
    public void amelioreStats() {
        this.degats += 25 * (level.get()-1);
        this.attaqueSpeed *= 0.8;
        portee.set(portee.get()+5*level.get());

    }
}

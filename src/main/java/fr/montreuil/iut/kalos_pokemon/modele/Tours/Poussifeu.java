package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblagePrioritaire;

public class Poussifeu extends Tour {
    public Poussifeu(int x, int y) {
        super(100, 30,
                PokemonEnum.poussifeu.getType(),
                PokemonEnum.poussifeu.getPrix(), x, y,
                PokemonEnum.poussifeu.name(), 50, new NullActif());

        setMyForgeEffectImpact(new ForgeEffetNull(this));
        setModeAttaque(new ModeCiblagePrioritaire(this));
        setMyForgeAttaque(new ForgeProjectileSimple());
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.3;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;

public class NewPoussifeu extends Tour{
    public NewPoussifeu(int x, int y) {
        super(100, 30, PokemonEnum.poussifeu.getType(),
                PokemonEnum.poussifeu.getPrix(), x, y, PokemonEnum.poussifeu.name(),
                50, new NullActif());
        ModeDeCiblage tirAleatoire = new ModeCiblageAleatoire(this);
        setModeAttaque(tirAleatoire);
        setMyForgeEffectImpact(new ForgeEffetNull(this));
        setMyForgeAttaque(new ForgeProjectileSimple());
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.5;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}

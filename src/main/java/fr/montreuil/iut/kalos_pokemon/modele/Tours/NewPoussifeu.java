package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewPoussifeu extends Tour{
    public NewPoussifeu(int x, int y) {
        super(100, 30, PokemonEnum.poussifeu.getType(),
                PokemonEnum.poussifeu.getPrix(), x, y, PokemonEnum.poussifeu.name(),
                50, new NullActif());
        ModeAttaque tirAleatoire = new ModeTirAleatoire(this);
        setModeAttaque(tirAleatoire);
        setMyForgeEffectImpact(new ForgeEffetNull(this));
        setMyForgeAttaque(new ForgeProjectile());
    }

    @Override
    public void amelioreStats() {
        this.degats *= 1.5;
        this.attaqueSpeed -= 6* (getLevel()-1);
        portee.set(portee.get()+(3* getLevel()));
    }
}

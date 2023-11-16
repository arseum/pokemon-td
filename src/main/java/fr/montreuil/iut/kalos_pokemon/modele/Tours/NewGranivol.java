package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirEnContinue;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewGranivol extends Tour{
    public NewGranivol(int x, int y) {

        super(160, 1, PokemonEnum.granivol.getType(),
                PokemonEnum.granivol.getPrix(), x, y, PokemonEnum.granivol.name(),
                6, new NullActif());
        //ModeAttaque modeAttaque = new ModeTirEnContinue(new EffetNull(this), this);
        //FIXME le temps de la prochaine attaque ca devrait etre le projectile et non l'effet
        // ok, ca vient de modeTirCon cette condition
        // ennemiCible.estEffecteParEffet(this.effetAttaque)
        // je l'avais implemente pour eviter bug visuel (genre ca attaque mais pas d'effet)
        ModeAttaque modeAttaque = new ModeTirEnContinue( this);
        setModeAttaque(modeAttaque);
        setMyForgeEffectImpact(new ForgeEffetNull(this));
        setMyForgeAttaque(new ForgeProjectile());
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

package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgePoisson;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirUnique;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.SlowEnnemiEmpoissone;

public class NewNidoran extends Tour{
    public NewNidoran(int x, int y) {
        super(115, 0, PokemonEnum.nidoran.getType(),
                PokemonEnum.nidoran.getPrix(), x, y, PokemonEnum.nidoran.name(),
                30, new NullActif());

//        this.effetPoison = new EffetPoison(2, new Seconde(3), new Seconde(0.2), this);
        ModeAttaque tirUniqueParEnnemi = new ModeTirUnique(this);
        setModeAttaque(tirUniqueParEnnemi);

        setMyForgeEffectImpact(new ForgePoisson(2, new Seconde(3),new Seconde(0.2),this));
        setMyForgeAttaque(new ForgeProjectile());
        setMyCompetence(new SlowEnnemiEmpoissone(this));
    }

    @Override
    public void amelioreStats() {
//        this.effetPoison.amelioreEffet(10,0);
        if (level.get() == Parametres.niveauEvolutionTour){
            portee.set(portee.get()+400);
//            ModeAttaque zone = new ModeZone(this.effetPoison, this);
//            setModeAttaque(zone);
        }
    }
}

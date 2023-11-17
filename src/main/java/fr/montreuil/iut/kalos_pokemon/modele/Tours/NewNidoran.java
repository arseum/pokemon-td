package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgePoisson;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeAttaqueDirect;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeBouleDeFeu;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeTirUnique;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.SlowEnnemiEmpoissone;

public class NewNidoran extends Tour{
    public NewNidoran(int x, int y) {
        super(350, 0, PokemonEnum.nidoran.getType(),
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
//            portee.set(portee.get()+400);
            setModeAttaque(new ModeZone(this));
            setMyForgeAttaque(new ForgeBouleDeFeu(100));
            setMyForgeEffectImpact(new ForgePoisson(5,new Seconde(2),new Seconde(0.1),this));
            degats = 0;
            attaqueSpeed = 110;
//            ModeAttaque zone = new ModeZone(this.effetPoison, this);
//            setModeAttaque(zone);
        }
        else{
            setModeAttaque(new ModeTirAleatoire(this));
            setMyForgeEffectImpact(new ForgeRalentissement(this,3));
            setMyForgeAttaque(new ForgeAttaqueDirect());
            degats = 45;
            attaqueSpeed = 60;
        }
    }
}

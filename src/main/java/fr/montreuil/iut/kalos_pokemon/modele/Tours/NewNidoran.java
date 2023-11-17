package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommageInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeProjectileSimpleExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblesUniques;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.SlowEnnemiEmpoissone;

public class NewNidoran extends Tour{
    public NewNidoran(int x, int y) {
        super(350, 0, PokemonEnum.nidoran.getType(),
                PokemonEnum.nidoran.getPrix(), x, y, PokemonEnum.nidoran.name(),
                30, new NullActif());

//        this.effetPoison = new EffetPoison(2, new Seconde(3), new Seconde(0.2), this);
        ModeDeCiblage tirUniqueParEnnemi = new ModeCiblesUniques(this);
        setModeAttaque(tirUniqueParEnnemi);

        setMyForgeEffectImpact(new ForgeEffetPoison(2, new Seconde(3),new Seconde(0.2),this));
        setMyForgeAttaque(new ForgeProjectileSimple());
        setMyCompetence(new SlowEnnemiEmpoissone(this));
    }

    @Override
    public void amelioreStats() {
//        this.effetPoison.amelioreEffet(10,0);
        if (level.get() == Parametres.niveauEvolutionTour){
//            portee.set(portee.get()+400);
            setModeAttaque(new ModeCiblageZone(this));
            setMyForgeAttaque(new ForgeProjectileSimpleExplosif(100));
            setMyForgeEffectImpact(new ForgeEffetPoison(5,new Seconde(2),new Seconde(0.1),this));
            degats = 0;
            attaqueSpeed = 110;
//            ModeAttaque zone = new ModeZone(this.effetPoison, this);
//            setModeAttaque(zone);
        }
        else{
            setModeAttaque(new ModeCiblageAleatoire(this));
            setMyForgeEffectImpact(new ForgeRalentissement(this,100, new Seconde(2)));
            setMyForgeAttaque(new ForgeEntiteDommageInstantane());
            degats = 45;
            attaqueSpeed = 60;
        }
    }
}

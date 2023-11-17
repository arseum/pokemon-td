package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.CompetenceRalentissementEnnemiPoison;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;

public class NewNidoran extends Tour{
    public NewNidoran(int x, int y) {
        super(350, 0, PokemonEnum.nidoran.getType(),
                PokemonEnum.nidoran.getPrix(), x, y, PokemonEnum.nidoran.name(),
                30, new NullActif());

//        this.effetPoison = new EffetPoison(2, new Seconde(3), new Seconde(0.2), this);
        /*
        ModeDeCiblage tirUniqueParEnnemi = new ModeCiblesUniques(this);
        setModeAttaque(tirUniqueParEnnemi);

        setMyForgeEffectImpact(new ForgeEffetPoison(50, new Seconde(1),new Seconde(0.2),this));
        setMyForgeAttaque(new ForgeProjectileSimple());
        setMyCompetence(new SlowEnnemiEmpoissone(this));

         */
        ModeDeCiblage modeDeCiblage = new ModeCiblageAleatoire(this);
        setModeAttaque(modeDeCiblage);
        setMyForgeEffectImpact(new ForgeEffetPoison(2, new Seconde(100),new Seconde(0.2),this));
        setMyForgeAttaque(new ForgeProjectileSimple());
        setMyCompetence(new CompetenceRalentissementEnnemiPoison(this, new Seconde(10)));
    }

    @Override
    public void amelioreStats() {
//        this.effetPoison.amelioreEffet(10,0);
        /*
        if (level.get() == Parametres.niveauEvolutionTour){
//            portee.set(portee.get()+400);
            setModeAttaque(new ModeCiblageZone(this));
            setMyForgeAttaque(new ForgeProjectileSimpleExplosif(100));
            setMyForgeEffectImpact(new ForgeEffetPoison(50,new Seconde(2),new Seconde(0.1),this));
            degats = 0;
            attaqueSpeed = 110;
//            ModeAttaque zone = new ModeZone(this.effetPoison, this);
//            setModeAttaque(zone);
        }
        else{
            setModeAttaque(new ModeCiblageAleatoire(this));
            setMyForgeEffectImpact(new ForgeRalentissement(this,100, new Seconde(2)));
            setMyForgeAttaque(new ForgeEntiteAttaqueInstantane());
            degats = 45;
            attaqueSpeed = 60;
        }

         */
    }
}

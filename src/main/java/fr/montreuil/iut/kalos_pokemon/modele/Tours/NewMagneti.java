package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.CompetenceImmobilisationZone;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;

public class NewMagneti extends Tour{
    public NewMagneti(int x, int y) {

        super(150, 0, PokemonEnum.magneti.getType(),
                PokemonEnum.magneti.getPrix(), x, y, PokemonEnum.magneti.name(),
                22, new NullActif());


        ModeDeCiblage modeZone = new ModeCiblageZone( this);
        setModeAttaque(modeZone);
        setMyForgeEffectImpact(new ForgeRalentissement(this,50,new Seconde(0.35)));
        setMyForgeAttaque(new ForgeEntiteAttaqueInstantane());
        setMyCompetence(new CompetenceImmobilisationZone(this,new Seconde(15),new Seconde(2.5)));


//        ModeDeCiblage modeDeCiblage = new ModeCiblageAleatoire(this);
//        setModeAttaque(modeDeCiblage);
//        setMyForgeEffectImpact(new ForgeRalentissement(this,50, new Seconde(5)));
//        setMyForgeAttaque(new ForgeProjectileSimple());
//        setMyCompetence(new CompetenceImmobilisationZone(this, new Seconde(5), new Seconde(3)));
    }

    @Override
    public void amelioreStats() {

    }
}

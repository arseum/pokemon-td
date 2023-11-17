package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageAleatoire;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;

public class NewMagneti extends Tour{
    public NewMagneti(int x, int y) {

        super(150, 0, PokemonEnum.magneti.getType(),
                PokemonEnum.magneti.getPrix(), x, y, PokemonEnum.magneti.name(),
                200, new NullActif());

        /*
        ModeAttaque modeZone = new ModeZone( this);
        setModeAttaque(modeZone);
        setMyForgeEffectImpact(new ForgeRalentissement(this,2));
        setMyForgeAttaque(new ForgeAttaqueDirect());
        setMyCompetence(new ImmobilisationZone(this,2));

         */
        ModeDeCiblage modeDeCiblage = new ModeCiblageAleatoire(this);
        setModeAttaque(modeDeCiblage);
        setMyForgeEffectImpact(new ForgeRalentissement(this,50, new Seconde(5)));
        setMyForgeAttaque(new ForgeProjectileSimple());
    }

    @Override
    public void amelioreStats() {

    }
}

package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommageInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NullActif;

public class NewMagneti extends Tour{
    public NewMagneti(int x, int y) {

        super(150, 0, PokemonEnum.magneti.getType(),
                PokemonEnum.magneti.getPrix(), x, y, PokemonEnum.magneti.name(),
                30, new NullActif());

        /*
        ModeAttaque modeZone = new ModeZone( this);
        setModeAttaque(modeZone);
        setMyForgeEffectImpact(new ForgeRalentissement(this,2));
        setMyForgeAttaque(new ForgeAttaqueDirect());
        setMyCompetence(new ImmobilisationZone(this,2));

         */
        ModeDeCiblage modeDeCiblage = new ModeCiblageZone(this);
        setModeAttaque(modeDeCiblage);
        setMyForgeEffectImpact(new ForgeRalentissement(this,20));
        setMyForgeAttaque(new ForgeEntiteDommageInstantane());
    }

    @Override
    public void amelioreStats() {

    }
}

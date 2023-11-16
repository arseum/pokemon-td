package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeAttaqueDirect;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.ImmobilisationZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewMagneti extends Tour{
    public NewMagneti(int x, int y) {

        super(150, 0, PokemonEnum.magneti.getType(),
                PokemonEnum.magneti.getPrix(), x, y, PokemonEnum.magneti.name(),
                30, new NullActif());

        ModeAttaque modeZone = new ModeZone( this);
        setModeAttaque(modeZone);
        setMyForgeEffectImpact(new ForgeRalentissement(this,2));
        setMyForgeAttaque(new ForgeAttaqueDirect());
        setMyCompetence(new ImmobilisationZone(this,2));
    }

    @Override
    public void amelioreStats() {

    }
}

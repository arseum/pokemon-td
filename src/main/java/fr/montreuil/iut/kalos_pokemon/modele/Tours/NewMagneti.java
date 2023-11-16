package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewMagneti extends Tour{
    private EffetRalentissement effetRalentissement;
    public NewMagneti(int x, int y) {
        super(300, 5, Pokemon.magneti.getType(), Pokemon.magneti.getPrix(), x, y, Pokemon.magneti.name(), 30, new NullActif());
        this.effetRalentissement = new EffetRalentissement(this, 9);
        ModeAttaque modeZone = new ModeZone( this);
        setModeAttaque(modeZone);
        setMyForgeEffectImpact(new ForgeRalentissement(this,10));
    }

    @Override
    public void amelioreStats() {

    }
}

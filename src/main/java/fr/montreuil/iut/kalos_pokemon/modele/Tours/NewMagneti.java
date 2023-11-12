package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.NullActif;

public class NewMagneti extends Tour{
    private EffetRalentissement effetRalentissement;
    public NewMagneti(int x, int y) {
        super(300, 0, Pokemon.magneti.getType(), Pokemon.magneti.getPrix(), x, y, Pokemon.magneti.name(), 0, new NullActif());
        this.effetRalentissement = new EffetRalentissement(this, 9);
        ModeAttaque modeZone = new ModeZone(this.effetRalentissement, this);
        setModeAttaque(modeZone);
    }

    @Override
    public void amelioreStats() {

    }
}

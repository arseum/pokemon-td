package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.Poison;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.Zone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;

public class TourZonePoison extends Tour{
    public TourZonePoison(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed, Competence competence) {
        super(portee, degats, type, prix, x, y, pokemon, attaqueSpeed, competence);
        ModeAttaque m = new Zone(new Poison(2,new Seconde(10),new Seconde(0.2), this),this);
        setModeAttaque(m);
    }

    @Override
    public void amelioreStats() {

    }
}

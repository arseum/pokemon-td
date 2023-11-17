package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donnees.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Ludicolo extends Ennemi {
    public Ludicolo(int x, int y) {
        //super(2, 250, "eau", x, y, 45, "ludicolo", game, true);
        super(2, 1700, Type.eau, x, y, 50, "ludicolo", true);
    }

    @Override
    protected void meurt() {
        super.meurt();
        Game.getGame().ajouteEnnemi(new Nenupiot((x.get()/Parametres.tailleTuile)*Parametres.tailleTuile, (y.get()/ Parametres.tailleTuile)*Parametres.tailleTuile));

    }
}

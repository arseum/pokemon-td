package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Ludicolo extends Ennemi {
    public Ludicolo(int x, int y) {
        //super(2, 250, "eau", x, y, 45, "ludicolo", game, true);
        super(2, 250, "eau", x, y, 45, "ludicolo", true);
    }

    @Override
    protected void meurt() {
        super.meurt();
        Game.getGame().ajouteEnnemi(new Nenupiot((x.get()/Parametres.tailleTuile)*Parametres.tailleTuile, (y.get()/ Parametres.tailleTuile)*Parametres.tailleTuile));
    }
}

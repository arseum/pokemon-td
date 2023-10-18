package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.ExplosionAutourTour;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.TypeProjectile.bouleDeFeu;

import java.util.ArrayList;

public class Salameche extends Tour {

    private int rayonExploxion;

    public Salameche(int x, int y) {
        super(98, 50, "feu", Parametres.prixsalameche, x, y, "salameche", 40, null);
        rayonExploxion = 57;
        setMyCompetence(new ExplosionAutourTour(this));
    }

    @Override
    public void amelioreStats() {
        rayonExploxion += 10;
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get()-1));
    }



    @Override
    public void lanceProjectile(Ennemi cible, ArrayList<EffetImpact> listEffect) {
        game.ajouteProjectile(new bouleDeFeu(this,cible,game));
    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
}

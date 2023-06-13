package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.bouleDeFeu;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Salameche extends Tour {

    private int rayonExploxion;
    private IntegerProperty actif;
    public Salameche(int x, int y) {
        super(90, 30, "feu", Parametres.prixsalameche, x, y, "salameche", 50);
        rayonExploxion = 57;
        actif = new SimpleIntegerProperty(0);
    }

    public IntegerProperty actifProperty() {
        return actif;
    }

    @Override
    public void levelUp() {
        this.level.set(level.get()+1);
        rayonExploxion = 75;
    }

    @Override
    public void actif() {

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            if (Parametres.distance(listEnnemi.get(i),this) <= portee.get() ) {
                listEnnemi.get(i).diminueHP(150);
            }
        }

        tempProchainActif = game.getNbFrame() + (60*25) ;

        //permet de provoquer une animation dans la vue
        actif.set(actif.get()+1);
    }

    @Override
    protected void lanceProjectile(Ennemi cible) {
        game.ajouteProjectile(new bouleDeFeu(this,cible,game));
    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
}

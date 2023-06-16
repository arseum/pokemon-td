package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.bouleDeFeu;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Salameche extends TourActif {

    private int rayonExploxion;
    private IntegerProperty actif;
    public Salameche(int x, int y) {
        super(98, 50, "feu", Parametres.prixsalameche, x, y, "salameche", 40);
        rayonExploxion = 57;
        actif = new SimpleIntegerProperty(0);
    }

    public IntegerProperty actifProperty() {
        return actif;
    }

    @Override
    protected void amelioreStats() {
        rayonExploxion += 10;
        degats *= 1.2;
        portee.set(portee.get() + 10 * (level.get()-1));
    }

    @Override
    public void actif() {

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();
        Ennemi e;
        double damage;

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            e = listEnnemi.get(i);
            if (estADistance(e) ) {
                damage = Parametres.calculDegats(type,e.getType(),150);
                e.diminueHP(damage);
                ajouteDegats(damage);
            }
        }
        tempProchainActif.set(game.getNbFrameValue() + (60*22)) ;

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

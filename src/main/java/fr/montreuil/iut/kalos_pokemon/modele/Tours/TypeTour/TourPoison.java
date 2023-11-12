package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.SlowEnnemiEmpoissone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourPoison extends TourAvecType implements TourSpe {

    protected ObservableList<Ennemi> ennemiEmpoisone;
    protected int degatsPoison;
    private Seconde dureePoison;
    public TourPoison(Tour t, int degatsPoison, Seconde dureePoison) {
        super(t);
        ennemiEmpoisone = FXCollections.observableArrayList();
        this.degatsPoison = degatsPoison;
        this.dureePoison = dureePoison;
        setMyCompetence(new SlowEnnemiEmpoissone(this));
    }

    public int getDegatsPoison() {
        return degatsPoison;
    }


    protected boolean peutCibler(Ennemi ennemi) {
        return !ennemiEmpoisone.contains(ennemi) && estADistance(ennemi);
    }

    public void ajouteEnnemiEmpoissoner(Ennemi e){
        ennemiEmpoisone.add(e);
    }

    public void supprimerEnnemiEmpoissoner(Ennemi e) {ennemiEmpoisone.remove(e);}

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }

    @Override
    public void amelioreStats() {
        myTour.amelioreStats();
        degatsPoison += 2; //todo a verfier ct combien jspu
    }


    protected void lanceProjectile(Ennemi cible) {
        //game.ajouteProjectile(new Projectile(this, cible, game, new Poison(degatsPoison,dureePoison,new Seconde(0.2), this)));
        //Game.getGame().ajouteProjectile(new Projectile(this, cible, new Poison(degatsPoison,dureePoison,new Seconde(0.2), this)));
        ajouteEnnemiEmpoissoner(cible);
    }

}

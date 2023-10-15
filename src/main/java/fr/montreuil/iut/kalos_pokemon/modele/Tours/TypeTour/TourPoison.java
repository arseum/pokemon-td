package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.Poison;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
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

    @Override
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

    @Override
    protected void lanceProjectile(Ennemi cible) {
        game.ajouteProjectile(new Projectile(this, cible, game, new Poison(degatsPoison,dureePoison,new Seconde(0.2), this)));
        ajouteEnnemiEmpoissoner(cible);
    }

    @Override
    public void appliqueEffet() {
        //TODO on part du principe que le poison est permanent, c'est peut-etre a revoir ?
        Ennemi ennemi;
        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemi = ennemiEmpoisone.get(i);
            if (ennemi.getHp() > 0 && !ennemi.isEstArrive()) { //si la cible est mort/arrivé entre temps
                ennemi.diminueHP(degatsPoison);
                //le poisson etant neutre pas besoin de faire un calcul avec les types
                ajouteDegats(degatsPoison);
            }
            else
                ennemiEmpoisone.remove(i);
        }
    }
}

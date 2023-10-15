package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class TourPoison extends TourAvecType implements TourSpe {

    protected ObservableList<Ennemi> ennemiEmpoisone;
    protected int degatsPoison;
    public TourPoison(Tour t, int degatsPoison) {
        super(t);
        ennemiEmpoisone = FXCollections.observableArrayList();
        this.degatsPoison = degatsPoison;
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

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
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

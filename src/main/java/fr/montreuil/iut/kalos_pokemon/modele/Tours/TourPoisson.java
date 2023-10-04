package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class TourPoisson extends TourConcrete {

    protected ObservableList<Ennemi> ennemiEmpoisone;
    protected int degatsPoison;

    public TourPoisson(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed, int degatsPoison) {
        super(portee, degats, type, prix, x, y, pokemon, attaqueSpeed);

        ennemiEmpoisone = FXCollections.observableArrayList();
        this.degatsPoison = degatsPoison;
    }

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }

    public void ajouteEnnemiEmpoissoner(Ennemi e){
        ennemiEmpoisone.add(e);
    }

    public void apliquePoison() {
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

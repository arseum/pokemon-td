package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ModeDattaque;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.Poison;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.SlowEnnemiEmpoissone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SpecialPoison extends Tireur{

    protected ObservableList<Ennemi> ennemiEmpoisone;

    public SpecialPoison(Tour myTour) {
        super(myTour, new ArrayList<>() {{add(new Poison(2,new Seconde(5), new Seconde(0.2),myTour));}});
        ennemiEmpoisone = FXCollections.observableArrayList();
        myTour.setMyCompetence(new SlowEnnemiEmpoissone(this, t));

    }

    public void ajouteEnnemiEmpoissoner(Ennemi e){
        ennemiEmpoisone.add(e);
    }

    public void supprimerEnnemiEmpoissoner(Ennemi e) {ennemiEmpoisone.remove(e);}

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }


    @Override
    public void attaque() {

    }
}

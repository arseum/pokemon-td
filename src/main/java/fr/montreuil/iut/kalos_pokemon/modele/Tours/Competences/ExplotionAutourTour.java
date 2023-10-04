package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourConcrete;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class ExplotionAutourTour extends TourAvecCompetence {

    private IntegerProperty actif;

    public ExplotionAutourTour(TourConcrete tourConcrete) {
        super(tourConcrete);
        actif = new SimpleIntegerProperty(0);
    }

    public IntegerProperty actifProperty() {
        return actif;
    }

    @Override
    public void actif() {

        List<Ennemi> listEnnemi = myTour.getGame().getListEnnemi().stream().toList();
        Ennemi e;
        double damage;

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            e = listEnnemi.get(i);
            if (myTour.estADistance(e) ) {
                damage = Parametres.calculDegats(myTour.getType(),e.getType(),150);
                e.diminueHP(damage);
                myTour.ajouteDegats(damage);
            }
        }
        tempProchainActif.set(myTour.getGame().getNbFrameValue() + (60*22)) ;

        //permet de provoquer une animation dans la vue
        actif.set(actif.get()+1);
    }
}

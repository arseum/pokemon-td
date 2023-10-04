package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourPoisson;

import java.util.List;

public class RalentiEnnemiEmpoisone extends TourAvecCompetence{

    private TourPoisson myTourPoisson;

    public RalentiEnnemiEmpoisone(TourPoisson tour) {
        super(tour);
        myTourPoisson = tour;
    }

    @Override
    public void actif() {

        List<Ennemi> ennemiEmpoisoner = myTourPoisson.getEnnemiEmpoisone();

         for (int i = ennemiEmpoisoner.size() - 1; i >= 0; i--) {
            ennemiEmpoisoner.get(i).reduitVitesseMax(1);
        }

        tempProchainActif.set(myTour.getGame().getNbFrameValue() + (60*12));

    }
}

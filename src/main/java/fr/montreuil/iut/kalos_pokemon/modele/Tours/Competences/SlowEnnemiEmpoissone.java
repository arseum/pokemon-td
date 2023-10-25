package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour.TourPoison;

import java.util.List;

public class SlowEnnemiEmpoissone extends ClassicCompetence{
    private TourPoison myTourPoisson;
    public SlowEnnemiEmpoissone(TourPoison myTourPoisson, Tour myTour) {
        super(myTour, new Seconde(12));
        this.myTourPoisson = myTourPoisson;
    }

    @Override
    public void actif() {

        //TODO cette actif est un peu bizarre...
        // pourquoi pas faire en sorte qu'il reset le poison + applique un ralentissement durant la nouvelle perdiode de poison
        List<Ennemi> ennemiEmpoisone = myTourPoisson.getEnnemiEmpoisone();

        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).reduitVitesseMax(1);
        }

        tempProchainActif.set(myTour.getGame().getNbFrameValue() + cooldown.getTempFrameInt());

    }


}

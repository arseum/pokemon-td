package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourConcrete;

import java.util.List;

public class ImmobilisationZone extends TourAvecCompetence{

    private int dureeStun;

    public ImmobilisationZone(TourConcrete tourConcrete) {
        super(tourConcrete);
        dureeStun = 90;
    }


    @Override
    public void actif() {

        List<Ennemi> listEnnemi = myTour.getGame().getListEnnemi().stream().toList();

        for (Ennemi e : listEnnemi) {
            if (Parametres.distance(this,e) <= myTour.getPortee())
                e.stun(dureeStun);
        }

        tempProchainActif.set(myTour.getGame().getNbFrameValue() + (60*18));
    }


}

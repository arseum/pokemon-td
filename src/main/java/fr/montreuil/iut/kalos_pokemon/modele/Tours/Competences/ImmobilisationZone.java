package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;


import java.util.List;

public class ImmobilisationZone extends ClassicCompetence {

    private int dureeStun;

    public ImmobilisationZone(Tour myTour, int dureeStun) {
        super(myTour);
        this.dureeStun = dureeStun;
    }

    public void setDureeStun(int dureeStun) {
        this.dureeStun = dureeStun;
    }

    @Override
    public void actif() {
        List<Ennemi> listEnnemi = myTour.getGame().getListEnnemi().stream().toList();

        for (Ennemi e : listEnnemi) {
            if (Parametres.distance(myTour,e) <= myTour.getPortee())
                e.stun(dureeStun);
        }

        //TODO mettre le cooldown en parametre de ClassicCompetences pour mettre cette ligne plus haut et rajouter un type pour les secondes
        tempProchainActif.set(myTour.getGame().getNbFrameValue() + (60*18));
    }



}

package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ModeDattaque;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

public abstract class Tireur extends ModeAttaque {

    public Tireur(Tour myTour) {
        super(myTour);
    }

    public void attaque() {

        Ennemi cible = chercheCible();

        if (cible != null) {
            lanceProjectile(cible,new ArrayList<>());
            tempProchaineAttaque = game.getNbFrameValue() + attaqueSpeed;
        }

    }

    private Ennemi chercheCible() {
        Ennemi cible = null;
        int index = 0;

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //cherche une cible
        while (cible == null && index < listEnnemi.size()) {

            if (peutCibler(listEnnemi.get(index)))
                cible = listEnnemi.get(index);
            else
                index++;

        }
        return cible;
    }



}

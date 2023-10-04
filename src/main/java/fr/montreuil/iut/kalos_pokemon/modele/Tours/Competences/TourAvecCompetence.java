package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourConcrete;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class TourAvecCompetence implements Tour {

    protected IntegerProperty tempProchainActif;
    protected BooleanProperty estPretActif;
    protected TourConcrete myTour;

    public TourAvecCompetence(TourConcrete tourConcrete) {
        myTour = tourConcrete;
        tempProchainActif = new SimpleIntegerProperty(1000000);
        //grand nombre pour empecher que le boolean est pret passe a true dés le debut du jeu
        estPretActif = new SimpleBooleanProperty(false);
    }

    @Override
    public void evolution() {
        myTour.evolution();
        tempProchainActif.set(myTour.getGame().getNbFrameValue());
    }

    public abstract void actif();

    public BooleanProperty getEstPretActif() {return estPretActif;}
    public IntegerProperty getTempProchainActif() {return tempProchainActif;}
    public boolean isEstPretActif() {
        return estPretActif.get();
    }

    @Override
    public int getX() {
        return myTour.getX();
    }

    @Override
    public int getY() {
        return myTour.getY();
    }
    public String getId(){ return myTour.getId() ;}
    public int getPrix() { return myTour.getPrix(); }
    public String getNom() { return  myTour.getNom(); }
    public int getPortee() { return  myTour.getPortee(); }
    int prixRevente();
     int prixAmelioration();
     void levelUp();
     int getLevel();
     int getTempProchaineAttaque();
     void attaque();
     void setGame(Game g);

}

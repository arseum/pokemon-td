package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourConcrete;
import javafx.beans.property.*;

public abstract class TourAvecCompetence extends Tour {

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

    @Override
    public double getCompteurDegats() {
        return myTour.getCompteurDegats();
    }

    @Override
    public int getPortee() {
        return myTour.getPortee();
    }

    @Override
    public int getDegats() {
        return myTour.getDegats();
    }

    @Override
    public String getType() {
        return myTour.getType();
    }

    @Override
    public String getNom() {
        return myTour.getNom();
    }

    @Override
    public int getPrix() {
        return myTour.getPrix();
    }

    @Override
    public int getAttaqueSpeed() {
        return myTour.getAttaqueSpeed();
    }

    @Override
    public String getId() {
        return myTour.getId();
    }

    @Override
    public int getTempProchaineAttaque() {
        return myTour.getTempProchaineAttaque();
    }

    @Override
    public Game getGame() {
        return myTour.getGame();
    }

    @Override
    public int getLevel() {
        return myTour.getLevel();
    }

    @Override
    public IntegerProperty porteeProperty() {
        return myTour.porteeProperty();
    }

    @Override
    public void amelioreStats() {
        myTour.amelioreStats();
    }

    @Override
    public IntegerProperty levelProperty() {
        return myTour.levelProperty();
    }

    @Override
    public IntegerProperty xProperty() {
        return myTour.xProperty();
    }

    @Override
    public IntegerProperty yProperty() {
        return myTour.yProperty();
    }

    @Override
    public DoubleProperty compteurDegatsProperty() {
        return myTour.compteurDegatsProperty();
    }

    @Override
    public void setGame(Game game) {
        super.setGame(game);
        myTour.setGame(game);
    }

    @Override
    public void setLevel(int level) {
        myTour.setLevel(level);
    }

    @Override
    public void attaque() {
        myTour.attaque();
    }
}


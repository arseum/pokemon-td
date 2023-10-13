package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ClassicCompetence implements Competence{

    protected IntegerProperty tempProchainActif;
    protected BooleanProperty estPretActif;

    protected Tour myTour;

    public ClassicCompetence(Tour myTour) {
        this.myTour = myTour;
        tempProchainActif = null;
        estPretActif = new SimpleBooleanProperty(false);
    }

    @Override
    public void setTempProchainActif(int t) {
        if (tempProchainActif == null)
            tempProchainActif = new SimpleIntegerProperty(t);
        else
            this.tempProchainActif.set(t);
    }

    @Override
    public BooleanProperty estPretActifProperty() {
        return estPretActif;
    }

    @Override
    public IntegerProperty tempProchainActifProperty() {
        return tempProchainActif;
    }

    @Override
    public boolean isEstPretActif() {
        return estPretActif.get();
    }
}

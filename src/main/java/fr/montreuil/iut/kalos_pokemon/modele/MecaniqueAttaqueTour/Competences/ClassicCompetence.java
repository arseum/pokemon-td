package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ClassicCompetence implements Competence{

    protected IntegerProperty tempProchainActif;
    protected BooleanProperty estPretActif;
    protected Seconde cooldown;
    protected Tour myTour;

    public ClassicCompetence(Tour myTour,Seconde cooldown) {
        this.myTour = myTour;
        tempProchainActif = null;
        estPretActif = new SimpleBooleanProperty(false);
        this.cooldown = cooldown;
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

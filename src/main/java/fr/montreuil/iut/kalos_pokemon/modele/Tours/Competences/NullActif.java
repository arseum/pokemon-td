package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public class NullActif implements Competence{
    @Override
    public void actif() {

    }

    @Override
    public BooleanProperty estPretActifProperty() {
        return null;
    }

    @Override
    public IntegerProperty tempProchainActifProperty() {
        return null;
    }

    @Override
    public boolean isEstPretActif() {
        return false;
    }

    @Override
    public void setTempProchainActif(int t) {

    }
}

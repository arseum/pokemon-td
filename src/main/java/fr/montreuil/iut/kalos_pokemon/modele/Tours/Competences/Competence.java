package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public interface Competence {

    void actif();
    BooleanProperty estPretActifProperty();
    IntegerProperty tempProchainActifProperty();
    boolean isEstPretActif();
    void setTempProchainActif(int t);

}

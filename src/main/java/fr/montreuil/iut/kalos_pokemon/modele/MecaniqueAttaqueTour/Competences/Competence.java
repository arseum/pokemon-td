package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public interface Competence {
    void activerCompetence();

    BooleanProperty estPretActifProperty();

    IntegerProperty tempProchainActifProperty();

    boolean isEstPretActif();

    void setTempProchainActif(int t);

}

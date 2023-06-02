package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;

public interface TourZone {

    IntegerProperty xProperty();

    IntegerProperty yProperty();

    ObservableList<Ennemi> getEnnemisCible();

    BooleanProperty actifProperty();

    String getNom();

}

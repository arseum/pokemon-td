package fr.montreuil.iut.kalos_pokemon.Controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;

/**
 * Permet de changer le curseur de la souris lorsqu'une tour est en cours d'ajout
 */
public class ObsTourEnCoursAjout implements ChangeListener<Boolean> {
    private final BorderPane scene;
    public ObsTourEnCoursAjout(BorderPane scene) {
        this.scene = scene;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean ancien, Boolean nouveau) {
        if (nouveau) scene.setCursor(Cursor.CLOSED_HAND);
        else scene.setCursor(Cursor.DEFAULT);
    }
}

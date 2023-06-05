package fr.montreuil.iut.kalos_pokemon.Controlleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;

public class ObsTourEnCoursAjout implements ChangeListener<Boolean> {

    private BorderPane scene;

    public ObsTourEnCoursAjout(BorderPane scene){
        this.scene = scene;
    }
    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean ancien, Boolean nouveau) {
        if(nouveau) scene.setCursor(Cursor.CLOSED_HAND);
        else scene.setCursor(Cursor.DEFAULT);
    }
}

package fr.montreuil.iut.kalos_pokemon.Controlleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ObsTourCarteSelectionnee implements ChangeListener<String> {

    private Label nomTourMenu;
    private StackPane imageTourMenu;
    private ObsClicSurTour obsClicSurTour;


    public ObsTourCarteSelectionnee(Label nomTourMenu, StackPane imageTourMenu, ObsClicSurTour obsClicSurTour){
        this.nomTourMenu = nomTourMenu;
        this.imageTourMenu = imageTourMenu;
        this.obsClicSurTour = obsClicSurTour;
    }


    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        nomTourMenu.setText(t1);
        imageTourMenu.lookup("#" + "backgroundImageSelection").toFront();
        imageTourMenu.lookup("#" + t1 + "ImageSelection").toFront();
    }
}

package fr.montreuil.iut.kalos_pokemon.Controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Permet de mettre à jour l'image et le nom du panneau de caractéristique de la tour selectionnée de la map
 */
public class ObsTourCarteSelectionnee implements ChangeListener<String> {
    private Label nomTourMenu;
    private StackPane imageTourMenu;

    public ObsTourCarteSelectionnee(Label nomTourMenu, StackPane imageTourMenu){
        this.nomTourMenu = nomTourMenu;
        this.imageTourMenu = imageTourMenu;
    }


    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        nomTourMenu.setText(t1);

        imageTourMenu.lookup("#" + "backgroundImageSelection").toFront();
        imageTourMenu.lookup("#" + t1 + "ImageSelection").toFront();
    }
}

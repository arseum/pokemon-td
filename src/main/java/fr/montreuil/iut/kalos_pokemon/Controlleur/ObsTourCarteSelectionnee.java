package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ObsTourCarteSelectionnee implements ChangeListener<String> {
    private Label nomTourMenu;
    private Label niveauTour;
    private StackPane imageTourMenu;
    private ObsClicSurTour obsClicSurTour;
    private Button vendreTourMenu;
    private Button ameliorerTourMenu;

    public ObsTourCarteSelectionnee(Label nomTourMenu, Label niveauTour, StackPane imageTourMenu, ObsClicSurTour obsClicSurTour, Button vendreTourMenu, Button ameliorerTourMenu){
        this.nomTourMenu = nomTourMenu;
        this.niveauTour = niveauTour;
        this.imageTourMenu = imageTourMenu;
        this.obsClicSurTour = obsClicSurTour;
        this.vendreTourMenu = vendreTourMenu;
        this.ameliorerTourMenu = ameliorerTourMenu;
    }


    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        Tour t = obsClicSurTour.game.retourneTourAPartirId(obsClicSurTour.idTourSelectionnee.get());
        nomTourMenu.setText(t1);

        imageTourMenu.lookup("#" + "backgroundImageSelection").toFront();
        imageTourMenu.lookup("#" + t1 + "ImageSelection").toFront();


        if(t != null){
            niveauTour.setText("Niveau " + t.getLevel());
            vendreTourMenu.setText("Vendre (" + t.prixRevente() + "$)");
            ameliorerTourMenu.setText("Améliorer (" + t.prixAmelioration() + "$)");
        }


    }
}

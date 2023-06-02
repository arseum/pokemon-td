package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CreateurMenu {
    private String[] listeTour;

    public CreateurMenu(String[] listeTour){
        this.listeTour = listeTour;
    }

    public void creationMenu(HBox conteneur){
        for (String nom : this.listeTour){
            conteneur.getChildren().add(spriteTour(nom));
        }
    }

    private VBox spriteTour(String nom){
        VBox contenantTour = new VBox();
        StackPane spriteTour;
        ImageView i, i_bw;
        Label prix = new Label(Parametres.prixTour(nom) + " $");

        //alignment="CENTER" prefHeight="101.0" prefWidth="85.0"

        contenantTour.setAlignment(Pos.CENTER);
        contenantTour.setPrefHeight(101);
        contenantTour.setPrefWidth(85);

        i = new ImageView(new Image("file:" + Parametres.cheminIconeTour + nom + ".png"));
        i.setId("tourMenuSprite_" + nom + "_normal");
        i_bw = new ImageView(new Image("file:" + Parametres.cheminIconeTour + nom + "_bw.png"));
        i_bw.setId("tourMenuSprite_" + nom + "_grise");

        spriteTour = new StackPane(i_bw, i);
        spriteTour.setId("tourMenuSprite_" + nom);

        contenantTour.getChildren().add(spriteTour);
        contenantTour.getChildren().add(prix);


        //A voir si bug
        /*
        i.setPickOnBounds(false);
        i_bw.setPickOnBounds(false);
        spriteTour.setPickOnBounds(true);

         */



        return contenantTour;
    }
}

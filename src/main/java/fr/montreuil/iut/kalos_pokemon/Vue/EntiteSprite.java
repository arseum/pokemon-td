package fr.montreuil.iut.kalos_pokemon.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class EntiteSprite {

    private ImageView hitBox;

    public EntiteSprite() {
    }

    public ImageView getHitBox() {
        return hitBox;
    }


    public static EntiteSprite creerEntite (String pokemon)throws IOException {

        EntiteSprite perso = new EntiteSprite();
        perso.hitBox = new ImageView(new Image(Objects.requireNonNull(EntiteSprite.class.getResource(pokemon+".png")).openStream()));

        return perso;
    }
}

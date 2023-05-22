package fr.montreuil.iut.kalos_pokemon.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class EntiteSprite {

    private ImageView hitBox;

    public EntiteSprite(String pokemon)throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(EntiteSprite.class.getResource(pokemon+".png")).openStream()));
    }

    public ImageView getHitBox() {
        return hitBox;
    }

}

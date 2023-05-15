package kalos.pokemon_td.Vue;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class EnnemiSprite {

    private ImageView hitBox;

    public EnnemiSprite() throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("togepi.png")).openStream()));
    }

    public ImageView getHitBox() {
        return hitBox;
    }

}

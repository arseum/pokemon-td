package kalos.pokemon_td.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class EnnemiSprite {

    private ImageView hitBox;
    private int x;
    private int y;

    public EnnemiSprite() throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("togepi.jpg")).openStream()));
        x = 64;
        y = 96;
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

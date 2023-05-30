package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class TirSprite {

    private ImageView hitBox;

    public TirSprite(Tour tour) throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(tour.getNom() + "_attaque_default.png")).openStream()));
        hitBox.setId("T" + tour.getId());
    }

    public ImageView getHitBox() {
        return hitBox;
    }

}

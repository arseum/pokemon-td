package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class TourSprite {

    private ImageView sprite;
    private Circle range;

    public TourSprite(Tour pokemon) throws IOException {
        sprite = new ImageView(new Image(Objects.requireNonNull(EnnemiSprite.class.getResource(pokemon.getNom() + ".png")).openStream()));
        range = new Circle(pokemon.getPortee());
        creationCercleRange();
    }

    private void creationCercleRange() {
        range.centerXProperty().bind(sprite.xProperty().add(sprite.getImage().getWidth()/2));
        range.centerYProperty().bind(sprite.yProperty().add(sprite.getImage().getHeight()/2));
        range.getStyleClass().add("rangeTour");
        range.setVisible(false);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Circle getRange() {
        return range;
    }
}

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
        sprite.setId(pokemon.getId());
        range = new Circle(pokemon.getPortee());
        creationCercleRange(pokemon);
    }

    private void creationCercleRange(Tour pokemon) {
        range.centerXProperty().bind(pokemon.xProperty());
        range.centerYProperty().bind(pokemon.yProperty());
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

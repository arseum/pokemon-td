package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class TourSprite {

    private ImageView sprite;
    private Circle range;
    private boolean clickActif;

    public TourSprite(Tour pokemon) throws IOException {
        sprite = new ImageView(new Image(Objects.requireNonNull(EnnemiSprite.class.getResource(pokemon.getNom() + ".png")).openStream()));
        clickActif = false;
        range = new Circle(pokemon.getPortee());
        creationCercleRange();
    }

    private void creationCercleRange() {
        range.centerXProperty().bind(sprite.xProperty());
        range.centerYProperty().bind(sprite.yProperty());
        range.getStyleClass().add("rangeTour");
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Circle getRange() {
        return range;
    }

    public boolean isClickActif() {
        return clickActif;
    }

    public void clickChange(){clickActif = !clickActif;}
}

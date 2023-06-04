package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class TourSprite {

    private final ImageView sprite;
    private final Circle range;

    public TourSprite(Tour pokemon) throws IOException {
        //sprite = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(pokemon.getNom() + ".png")).openStream()));
        sprite = new ImageView(new Image("file:" + Parametres.cheminIconeTour + pokemon.getNom() + ".png"));
        sprite.setId(pokemon.getId());
        range = new Circle(pokemon.getPortee());
        creationCercleRange(pokemon);
    }

    private void creationCercleRange(Tour pokemon) {
        range.centerXProperty().bind(pokemon.xProperty().add(sprite.getImage().getWidth()/2 - Parametres.offsetXTour));
        range.centerYProperty().bind(pokemon.yProperty().add(sprite.getImage().getHeight()/2 - Parametres.offsetYTour));
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

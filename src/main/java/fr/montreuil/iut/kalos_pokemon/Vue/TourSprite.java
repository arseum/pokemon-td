package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class TourSprite {

    private final ImageView sprite;
    private final Circle range;


    public TourSprite(Tour pokemon) {
        //sprite = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(pokemon.getNom() + ".png")).openStream()));
        sprite = new ImageView(new Image("file:" + Parametres.cheminIconeTour + pokemon.getNom() + ".png"));
        sprite.setId(pokemon.getId());
        range = new Circle();
        creationCercleRange(pokemon);
    }

    private void creationCercleRange(Tour pokemon) {
        range.radiusProperty().bind(pokemon.porteeProperty());
        range.setId("range_" + pokemon.getId());
        range.setMouseTransparent(true);
        range.centerXProperty().bind(pokemon.xProperty().add(sprite.getImage().getWidth() / 2 - Parametres.offsetXTour));
        range.centerYProperty().bind(pokemon.yProperty().add(sprite.getImage().getHeight() / 2 - Parametres.offsetYTour));
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

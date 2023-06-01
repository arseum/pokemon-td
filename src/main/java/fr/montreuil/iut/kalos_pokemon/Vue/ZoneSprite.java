package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.TourZone;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class ZoneSprite implements Sprite{
    private static int compteur = 1;
    private final ImageView hitBox;
    private BooleanProperty actif;
    private int idImage;
    private final String pokemonName;
    private final int nbImageMax;

    public ZoneSprite(TourZone tour) throws IOException {
        pokemonName = tour.getNom();
        nbImageMax = tour.getNbImageAdefault();
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(pokemonName + "_attaque_default_0.png")).openStream()));
        hitBox.setId("Zone_n°" + compteur);
        hitBox.getStyleClass().add("magneti_zone");
        compteur++;
        actif = new SimpleBooleanProperty(false);
        actif.bind(hitBox.visibleProperty());
        idImage = 0;
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    @Override
    public void bouge() throws IOException {
        for (int i = 0; i < 1; i++) {

            idImage++;
            if (idImage > nbImageMax)
                idImage = 0;

            hitBox.setImage(new Image(Objects.requireNonNull(getClass().getResource(pokemonName + "_attaque_default_" + idImage + ".png")).openStream()));
        }
    }

    public boolean isActif() {
        return actif.get();
    }

}

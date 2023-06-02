package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Attaque;
import fr.montreuil.iut.kalos_pokemon.modele.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class TirSprite {

    private ImageView hitBox;
    private String nomTireur;


    public TirSprite(Attaque a) throws IOException {
        nomTireur = a.getTireur().getNom();
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(nomTireur + "_attaque_default_0.png")).openStream()));
        hitBox.setId(a.getId());
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    public void updateImage(int idImage) throws IOException {
        hitBox.setImage(new Image(Objects.requireNonNull(getClass().getResource(nomTireur + "_attaque_default_" + idImage + ".png")).openStream()));
    }
}

package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class EnnemiSprite {

    private final ImageView hitBox;
    private ProgressBar barVie;

    public EnnemiSprite(Ennemi ennemi) throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(ennemi.getNom() + ".png")).openStream()));
        hitBox.setId(ennemi.getId());

        ennemi.setMaxHeightHitbox((int) hitBox.getImage().getHeight());
        ennemi.setMaxWidhtHitbox((int) hitBox.getImage().getWidth());

        barVie = new ProgressBar();
        barVie.layoutXProperty().bind(hitBox.xProperty());
        barVie.layoutYProperty().bind(hitBox.yProperty().add(-8));
        barVie.setPrefWidth( ((double)ennemi.getHpMax() - 100) / 900 * (50 - 25) + 25 );
        barVie.setPrefHeight(10);
        barVie.progressProperty().bind(ennemi.hpProperty().divide(ennemi.getHpMax()));
        barVie.setId("Bar_" + ennemi.getId());
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    public ProgressBar getBarVie() {
        return barVie;
    }
}

package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class EnnemiSprite {

    private final ImageView hitBox;
    private final ProgressBar barVie;
    private final Pane sprite;

    public EnnemiSprite(Ennemi ennemi) throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(ennemi.getNom() + ".png")).openStream()));
        hitBox.setId("hitbox_" + ennemi.getId());

        barVie = new ProgressBar();
        barVie.layoutXProperty().bind(hitBox.xProperty());
        barVie.layoutYProperty().bind(hitBox.yProperty().add(-8));
        barVie.setPrefWidth((ennemi.getHpMax() - 50) / 650 * (50 - 25) + 25);
        barVie.setPrefHeight(10);
        barVie.progressProperty().bind(ennemi.hpProperty().divide(ennemi.getHpMax()));

        sprite = new Pane(hitBox, barVie);
        sprite.setId(ennemi.getId());
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    public ProgressBar getBarVie() {
        return barVie;
    }

    public Pane getSprite() {
        return sprite;
    }
}

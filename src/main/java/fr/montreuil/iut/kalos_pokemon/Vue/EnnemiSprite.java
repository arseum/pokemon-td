package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EnnemiSprite {

    private final ImageView hitBox;
    private final ProgressBar barVie;
    private final Pane sprite;

    public EnnemiSprite(Ennemi ennemi){
        hitBox = new ImageView(Parametres.imagesPokemonMap.get(ennemi.getNom() + ".png"));
        hitBox.setId("hitbox_" + ennemi.getId());

        barVie = new ProgressBar();
        barVie.layoutXProperty().bind(hitBox.xProperty());
        barVie.layoutYProperty().bind(hitBox.yProperty().add(-8));
        barVie.setPrefWidth((ennemi.getHpMax() - 50) / 650 * (50 - 25) + 25);
        barVie.setPrefHeight(10);
        barVie.progressProperty().bind(ennemi.hpProperty().divide(ennemi.getHpMax()));

        sprite = new Pane(hitBox, barVie);
        sprite.setMouseTransparent(true);
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

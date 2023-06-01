package fr.montreuil.iut.kalos_pokemon.Vue;


import javafx.scene.image.ImageView;

import java.io.IOException;

public interface Sprite {

    void bouge() throws IOException ;

    boolean isActif();

    ImageView getHitBox();
}

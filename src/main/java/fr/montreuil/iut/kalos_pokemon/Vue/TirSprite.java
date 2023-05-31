package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class TirSprite {

    private ImageView hitBox;
    private static int compteur = 1;
    private ImageView cibleSprite;
    private boolean actif;

    public TirSprite(Tour tour) throws IOException {
        hitBox = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(tour.getNom() + "_attaque_default.png")).openStream()));
        hitBox.setId("Tir_n°" + compteur);
        compteur++;
        actif = true;
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    public ImageView getCibleSprite() {
        return cibleSprite;
    }

    public void setCibleSprite(ImageView ennemiSprite) {cibleSprite = ennemiSprite;}

    public boolean isActif() {
        return actif;
    }

    public void bouge(){
        if (hitBox.getX() > cibleSprite.getX() + 15 || hitBox.getY() > cibleSprite.getY() + 15 || hitBox.getX() < cibleSprite.getX() - 15 || hitBox.getY() < cibleSprite.getY() - 15 ){

            for (int i = 0 ; i < 6 ; i++){
                hitBox.setY( hitBox.getY() < cibleSprite.getY() ? hitBox.getY() + 1 : hitBox.getY() - 1 );
                hitBox.setX( hitBox.getX() < cibleSprite.getX() ? hitBox.getX() + 1 : hitBox.getX() - 1 );
            }


        }else {
            actif = false;
        }
    }

}

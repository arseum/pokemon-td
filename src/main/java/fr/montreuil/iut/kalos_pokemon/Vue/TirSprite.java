package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Attaque;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TirSprite {

    private final String nomTireur;
    private final ImageView hitBox;
    private int idImage;


    public TirSprite(Attaque a) {
        idImage = 0;
        nomTireur = a.getTireur().getNom();
        hitBox = new ImageView(Parametres.imagesTirMap.get(nomTireur + "_attaque_default_0.png"));
        hitBox.setId(a.getId());
        hitBox.setMouseTransparent(true);
    }

    public ImageView getHitBox() {
        return hitBox;
    }

    public void updateImage() throws IOException {
        idImage++;
        if (Parametres.imagesTirMap.get(nomTireur + "_attaque_default_" + idImage + ".png") == null)
            idImage = 0;
        hitBox.setImage(Parametres.imagesTirMap.get(nomTireur + "_attaque_default_" + idImage + ".png"));
    }
}

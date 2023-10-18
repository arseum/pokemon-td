package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.TypeProjectile.Attaque;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TirSprite {

    private final String nomTireur;
    private final ImageView hitBox;
    private int idImage;


    public TirSprite(Attaque a) {
        idImage = 0;

        String n = a.getTireur().getNom();
        nomTireur = Parametres.nomPetitEvolution.getOrDefault(n, n);
        // permet d'obtenir le nom de la plus petite evolution du tireur dans tout les cas car
        // le nom des fichiers est en fonction du nom de la petite evolution de chaque tour

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

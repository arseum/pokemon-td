package kalos.pokemon_td.Controlleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private TilePane mapContener;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Image tileSet;
        ImageView carre;
        try {
            tileSet = new Image(Objects.requireNonNull(getClass().getResource("tileset_pokemon.png")).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0 ; i < map.length ; i++)
            for (int j = 0 ; j < map[i].length ; j++){
                int n = map[i][j];
                carre = new ImageView(tileSet);

                switch (n){
                    case 2254:
                        carre.setViewport(new Rectangle2D(224,256,16,16));
                        break;
                    case 2257:
                        carre.setViewport(new Rectangle2D(272,256,16,16));

                        break;
                }

                mapContener.getChildren().add(carre);
            }

    }




    public void chargerMap(){
        try {
            // Ouvrir en lecture le fichier "scores.txt"
            BufferedReader reader = new BufferedReader(new FileReader("map_test_2.csv"));
            FileInputStream cheminInput = new FileInputStream("chemin.png");
            FileInputStream sableInput = new FileInputStream("sable.png");

            int numParse;

            Image tileChemin = new Image(cheminInput);
            Image tilesable= new Image(sableInput);

            // classe viewPort

            String ligne = reader.readLine();
            while (ligne != null) {
                if (ligne.length() > 0) {
                    String[] splitLigne = ligne.split(","); // Découper la ligne en utilisant la virgule comme séparateur.
                    for (String num:splitLigne ) {

                        if (Integer.parseInt(num) == 2257){
                            ImageView img = new ImageView(tileChemin);
                        }else {
                            ImageView img = new ImageView(tilesable);
                        }
                    }
                }
                ligne = reader.readLine();
            }
            // lorqu'on a terminé d'utiliser le fichier, ne pas oublier de fermer le reader.
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ce fichier n'existe pas.");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue pendant la lecture du fichier...");
        }
    }
}

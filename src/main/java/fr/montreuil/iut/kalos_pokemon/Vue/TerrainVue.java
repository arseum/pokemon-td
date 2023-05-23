package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TerrainVue {

    private TilePane mapVue;

    public TerrainVue(){
        mapVue = new TilePane();
    }

    public Rectangle2D carreDeDecoupe(int idTuile) {
        int tailleTuile, largeurTiledSet;
        largeurTiledSet = Parametres.largeurTileset;
        tailleTuile = Parametres.tailleTuile;


        int nbTuiles = largeurTiledSet / tailleTuile;
        int x = tailleTuile * (idTuile % nbTuiles);
        int y = tailleTuile * (idTuile / nbTuiles);


        return new Rectangle2D(x, y, tailleTuile, tailleTuile);
    }

    //public TilePane genereMap(ArrayList<ArrayList<Integer>> map){
    public TilePane genereMap(ArrayList<ArrayList<Integer>> map){

        //mapVue.setPrefColumns(map[0].length);
        //mapVue.setPrefRows(map.length);

        mapVue.setPrefColumns(map.get(0).size());
        mapVue.setPrefRows(map.size());

        Image tileSet;
        ImageView carre;
        try {
            //todo: Faire en sorte que le fichier lu soit celui dans Terrain
            tileSet = new Image(Objects.requireNonNull(getClass().getResource("the_tileset.png")).openStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //for(int[] ligne : map)
        for(ArrayList<Integer> ligne : map) {
            for (int a : ligne) {
                if(a == -1){
                    carre = new ImageView(tileSet);

                    carre.setViewport(carreDeDecoupe(Parametres.idTuileTransparente));

                    mapVue.getChildren().add(carre);
                }
                else {
                    carre = new ImageView(tileSet);

                    carre.setViewport(carreDeDecoupe(a));

                    mapVue.getChildren().add(carre);
                }
            }
        }

        return mapVue;
    }

}
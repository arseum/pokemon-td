package kalos.pokemon_td.Vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.Objects;

public class TerrainVue {

    private TilePane mapVue;

    public TerrainVue(){
        mapVue = new TilePane();
    }

    public Rectangle2D carreDeDecoupe(int idTuile) {
        int tailleTuile, largeurTiledSet;
        largeurTiledSet = 1056;
        tailleTuile = 32;


        int nbTuiles = largeurTiledSet / tailleTuile;
        int x = tailleTuile * (idTuile % nbTuiles);
        int y = tailleTuile * (idTuile / nbTuiles);


        return new Rectangle2D(x, y, tailleTuile, tailleTuile);
    }

        public TilePane genereMap(int[][] map){

        mapVue.setPrefColumns(map[0].length);
        mapVue.setPrefRows(map.length);

        Image tileSet;
        ImageView carre;
        try {
            tileSet = new Image(Objects.requireNonNull(getClass().getResource("the_tileset.png")).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int[] ligne : map)
            for(int a : ligne){
                carre = new ImageView(tileSet);

                carre.setViewport(carreDeDecoupe(a));

                mapVue.getChildren().add(carre);
            }

        return mapVue;
    }

}

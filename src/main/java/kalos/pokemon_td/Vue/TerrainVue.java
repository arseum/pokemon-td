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

    public TilePane genereMap(int[][] map){

        mapVue.setPrefColumns(map[0].length);
        mapVue.setPrefRows(map.length);

        Image tileSet;
        ImageView carre;
        try {
            tileSet = new Image(Objects.requireNonNull(getClass().getResource("tileset_pokemon.png")).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int[] ligne : map)
            for(int a : ligne){
                carre = new ImageView(tileSet);

                switch (a){
                    case 2254:
                        carre.setViewport(new Rectangle2D(224,256,16,16));
                        break;
                    case 2257:
                        carre.setViewport(new Rectangle2D(272,256,16,16));

                        break;
                }

                mapVue.getChildren().add(carre);
            }

        return mapVue;
    }

}

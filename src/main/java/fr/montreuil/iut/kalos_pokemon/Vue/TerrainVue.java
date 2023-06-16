package fr.montreuil.iut.kalos_pokemon.Vue;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TerrainVue {
    private final TilePane mapVue;

    public TerrainVue() {
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

    public TilePane genererMapAvecDecor(Terrain t) {
        ArrayList<ArrayList<Integer>> map = t.getArrierePlan();
        ArrayList<ArrayList<Integer>> mapDecor = t.getDecor();

        mapVue.setPrefColumns(map.get(0).size());
        mapVue.setPrefRows(map.size());

        Image tileSet;
        tileSet = new Image("file:" + Parametres.cheminTerrains + "tileset.png");

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).size(); j++) {

                ImageView carre;

                StackPane carreFinal;
                carre = new ImageView(tileSet);
                carre.setViewport(carreDeDecoupe(map.get(i).get(j)));

                if (mapDecor != null) {
                    ImageView carreDecor;

                    carreDecor = new ImageView(tileSet);
                    if (mapDecor.get(i).get(j) == -1) {
                        carreDecor.setViewport(carreDeDecoupe(Parametres.idTuileTransparente));
                    } else {
                        carreDecor.setViewport(carreDeDecoupe(mapDecor.get(i).get(j)));
                    }
                    carreFinal = new StackPane(carre, carreDecor);
                } else {
                    carreFinal = new StackPane(carre);
                }
                mapVue.getChildren().add(carreFinal);
            }
        }

        return mapVue;
    }

}
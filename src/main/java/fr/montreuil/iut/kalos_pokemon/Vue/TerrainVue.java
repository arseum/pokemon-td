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
    private TilePane mapVue;

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

    //public TilePane genereMap(ArrayList<ArrayList<Integer>> map){
    public TilePane genereMap(ArrayList<ArrayList<Integer>> map) {
        mapVue.setPrefColumns(map.get(0).size());
        mapVue.setPrefRows(map.size());

        Image tileSet;
        ImageView carre, carre2;
        try {
            tileSet = new Image(Objects.requireNonNull(getClass().getResource("the_tileset.png")).openStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //for(int[] ligne : map)
        for (ArrayList<Integer> ligne : map) {
            for (int a : ligne) {
                if (a == -1) {
                    carre = new ImageView(tileSet);

                    carre.setViewport(carreDeDecoupe(Parametres.idTuileTransparente));

                    mapVue.getChildren().add(carre);
                } else {
                    carre = new ImageView(tileSet);
                    carre.setViewport(carreDeDecoupe(a));


                    carre2 = new ImageView(tileSet);
                    carre2.setViewport(carreDeDecoupe(133));

                    StackPane carreFinal = new StackPane(carre, carre2);
                    mapVue.getChildren().add(carreFinal);
                    //mapVue.getChildren().add(carre2);
                }
            }
        }
        return mapVue;
    }

    public TilePane genererMapAvecDecor(Terrain t){
        ArrayList<ArrayList<Integer>> map = t.getArrierePlan();
        ArrayList<ArrayList<Integer>> mapDecor = t.getDecor();

        mapVue.setPrefColumns(map.get(0).size());
        mapVue.setPrefRows(map.size());

        Image tileSet;
        //tileSet = new Image(Parametres.cheminTerrains + "tileset.png");

        try {
            //todo Faire en sorte que le fichier lu soit celui dans Terrain
            tileSet = new Image(Objects.requireNonNull(getClass().getResource("the_tileset.png")).openStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < map.size(); i ++){
            for (int j = 0; j < map.get(0).size(); j++){
                ImageView carre;

                StackPane carreFinal;
                carre = new ImageView(tileSet);
                carre.setViewport(carreDeDecoupe(map.get(i).get(j)));

                if (mapDecor != null){
                    ImageView carreDecor;

                    carreDecor = new ImageView(tileSet);
                    if(mapDecor.get(i).get(j) == -1){
                        carreDecor.setViewport(carreDeDecoupe(Parametres.idTuileTransparente));
                    }
                    else {
                        carreDecor.setViewport(carreDeDecoupe(mapDecor.get(i).get(j)));
                    }
                    carreFinal = new StackPane(carre, carreDecor);
                }
                else {
                    carreFinal = new StackPane(carre);
                }
                mapVue.getChildren().add(carreFinal);
            }
        }

        return mapVue;
    }

}
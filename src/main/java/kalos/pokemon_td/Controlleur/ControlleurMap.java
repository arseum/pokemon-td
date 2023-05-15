package kalos.pokemon_td.Controlleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import kalos.pokemon_td.Vue.TerrainVue;
import kalos.pokemon_td.modele.Terrain;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private AnchorPane pane;
    private TilePane mapContener;

    private TerrainVue terrainVue;
    private Terrain terrain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        terrain = new Terrain();
        terrainVue = new TerrainVue();

        mapContener = terrainVue.genereMap(terrain.getMap_test());

        pane.getChildren().add(mapContener);

    }

}

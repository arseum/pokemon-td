package fr.montreuil.iut.kalos_pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(main.class.getResource("Vue/pokemon-dppt.ttf").toExternalForm(), 15);

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("acceuil.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pokemon TD !");
        stage.setWidth(1324);
        stage.setHeight(757);
        //stage.setPre
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

package fr.montreuil.iut.kalos_pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("acceuil.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pokemon TD !");
        stage.setWidth(1324);
        stage.setHeight(757);
        //stage.setPref
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

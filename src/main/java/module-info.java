module kalos.pokemon_td {
    requires javafx.controls;
    requires javafx.fxml;


    opens kalos.pokemon_td to javafx.fxml;
    exports kalos.pokemon_td;
}
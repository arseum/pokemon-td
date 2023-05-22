module kalos.pokemon_td {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.montreuil.iut.kalos_pokemon to javafx.fxml;
    exports fr.montreuil.iut.kalos_pokemon;
    exports fr.montreuil.iut.kalos_pokemon.Controlleur;
    opens fr.montreuil.iut.kalos_pokemon.Controlleur to javafx.fxml;
}
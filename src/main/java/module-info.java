module kalos.pokemon_td {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens fr.montreuil.iut.kalos_pokemon to javafx.fxml;
    exports fr.montreuil.iut.kalos_pokemon;
    exports fr.montreuil.iut.kalos_pokemon.Controleur;
    opens fr.montreuil.iut.kalos_pokemon.Controleur to javafx.fxml;
}
package fr.montreuil.iut.kalos_pokemon;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;
import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parametres {
    public static final int tailleTourX = 45;
    public static final int tailleTourY = 45;

    //Le niveau à partir duquel une tour "évolue" c-a-d qu'elle change d'apparence
    public static final int niveauEvolutionTour = 3;
    public static final double pourcentageRevente = 0.75;
    public static final int argentDepart = 550;
    public static final int argentDepartPourDev = 9999;

    public static String cheminTerrains = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Terrain/";
    public static String cheminInterface = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Interface/acceuil/";
    public static String cheminSpritePokemon = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Sprites/Pokemon/";
    public static String cheminTirSprite = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Sprites/Tir/";
    public static String cheminAudioCintya = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/cintya_music.mp3";

    //Parametres des chemin pour les tirs sprites

    public static String map;

    //Parametres des Tours
    public static int tailleTuile = 32;
    public static final int offsetXTour = (tailleTourX - tailleTuile) / 2;
    public static final int offsetYTour = (tailleTourY - tailleTuile) / 2;
    public static int largeurTileset = 1056;
    public static int hauteurTileset = 512;
    public static int nbTuilesLargueur = largeurTileset / tailleTuile;
    public static int nbTuilesHauteur = hauteurTileset / tailleTuile;
    public static int idTuileTransparente = 15;
    public static int debutZoneCheminTileSet = ((largeurTileset / tailleTuile / 2) + 1) * tailleTuile;
    public static int colonneZoneDepartTileSet = (largeurTileset / tailleTuile) - 3;
    public static int colonneZoneArriveeTileSet = (largeurTileset / tailleTuile) - 1;


    //map des images
    public static Map<String, Image> imagesTirMap = new HashMap<>();
    public static Map<String, Image> imagesPokemonMap = new HashMap<>();
    public static Map<String, String> nomPetitEvolution = new HashMap<>();

    public static void setMap(String s) {
        map = s;
    }

    public static void init() {

        File directory = new File(cheminTirSprite);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Image i = new Image("file:" + cheminTirSprite + file.getName());
                imagesTirMap.put(file.getName(), i);
            }
        }

        directory = new File(cheminSpritePokemon);
        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Image i = new Image("file:" + cheminSpritePokemon + file.getName());
                imagesPokemonMap.put(file.getName(), i);
            }
        }
        nomPetitEvolution.put(PokemonEnum.poussifeu.getNomEvolution(), PokemonEnum.poussifeu.name());
        nomPetitEvolution.put(PokemonEnum.granivol.getNomEvolution(), PokemonEnum.granivol.name());
        nomPetitEvolution.put(PokemonEnum.magneti.getNomEvolution(), PokemonEnum.magneti.name());
        nomPetitEvolution.put(PokemonEnum.salameche.getNomEvolution(), PokemonEnum.salameche.name());
        nomPetitEvolution.put(PokemonEnum.nidoran.getNomEvolution(), PokemonEnum.nidoran.name());
        nomPetitEvolution.put(PokemonEnum.grenousse.getNomEvolution(), PokemonEnum.granivol.name());
    }

    public static int distance(double x1, double y1, double x2, double y2) {
        double delta_x = Math.abs(x1 - x2);
        double delta_y = Math.abs(y1 - y2);
        return (int) Math.sqrt((delta_x * delta_x) + (delta_y * delta_y));
    }

}
package fr.montreuil.iut.kalos_pokemon;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
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

    public static final int argentDepart = 600;

    public static final int argentDepartPourDev = 9999;

    // Prix

    /*
    public static final int prixpoussifeu = 60;
    public static final int prixgranivol = 95;
    public static final int prixmagneti = 100;
    public static final int prixgrenousse = 155;
    public static final int prixnidoran = 150;
    public static final int prixsalameche = 130;


    // nom évolutions (pokemon niveau 3)
    public static final String nomEvolutionPoussifeu = "galifeu";
    public static final String nomEvolutionGranivol = "floravol";
    public static final String nomEvolutionMagneti = "magneton";
    public static final String nomEvolutionSalameche = "reptincel";
    public static final String nomEvolutionNidoran = "nidorino";
    public static final String nomEvolutionGrenousse = "croaporal";

*/
    //
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
    public static Map<String,String> nomPetitEvolution = new HashMap<>();
    //public static Map<String,String> nomGrandEvolution = new HashMap<>();


    // Types

    /*
    public static final String typeEau = "eau";
    public static final String typeFeu = "feu";
    public static final String typePlante = "plante";
    public static final String typeNeutre = "neutre";



    public static final double superEfficace = 1.3;
    public static final double peuEfficace = 0.7;

    public static double calculDegats(String typeAttaquant, String typeDefenseur, int degats){
        double multiplicateur = 1;
        switch (typeAttaquant) {
            case typeEau -> {
                switch (typeDefenseur) {
                    case typeEau, typePlante -> {
                        multiplicateur = peuEfficace;
                    }
                    case typeFeu -> {
                        multiplicateur = superEfficace;
                    }
                }
            }
            case typeFeu -> {
                switch (typeDefenseur) {
                    case typeEau, typeFeu -> {
                        multiplicateur = peuEfficace;
                    }
                    case typePlante -> {
                        multiplicateur = superEfficace;
                    }
                }
            }
            case typePlante -> {
                switch (typeDefenseur) {
                    case typeEau -> {
                        multiplicateur = superEfficace;
                    }
                    case typeFeu, typePlante -> {
                        multiplicateur = peuEfficace;
                    }
                }
            }
        }
        return degats * multiplicateur;
    }

     */

    public static void setMap(String s){
        map = s;
    }
/*
    public static int prixTour(String nom) {
        if (nom.equals("poussifeu")) return prixpoussifeu;
        else if (nom.equals("granivol")) return prixgranivol;
        else if (nom.equals("magneti")) return prixmagneti;
        else if (nom.equals("salameche")) return prixsalameche;
        else if (nom.equals("nidoran")) return prixnidoran;
        else if (nom.equals("grenousse")) return prixgrenousse;
        return -1;
    }

 */

    public static void init(){

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

        /*
        nomPetitEvolution.put(nomEvolutionPoussifeu,"poussifeu");
        nomPetitEvolution.put(nomEvolutionGranivol,"granivol");
        nomPetitEvolution.put(nomEvolutionMagneti,"magneti");
        nomPetitEvolution.put(nomEvolutionSalameche,"salameche");
        nomPetitEvolution.put(nomEvolutionNidoran,"nidoran");
        nomPetitEvolution.put(nomEvolutionGrenousse,"grenousse");

         */
        nomPetitEvolution.put(PokemonEnum.poussifeu.getNomEvolution(), PokemonEnum.poussifeu.name());
        nomPetitEvolution.put(PokemonEnum.granivol.getNomEvolution(), PokemonEnum.granivol.name());
        nomPetitEvolution.put(PokemonEnum.magneti.getNomEvolution(), PokemonEnum.magneti.name());
        nomPetitEvolution.put(PokemonEnum.salameche.getNomEvolution(), PokemonEnum.salameche.name());
        nomPetitEvolution.put(PokemonEnum.nidoran.getNomEvolution(), PokemonEnum.nidoran.name());
        nomPetitEvolution.put(PokemonEnum.grenousse.getNomEvolution(), PokemonEnum.granivol.name());
/*
        //Deprecié
        nomGrandEvolution.put("poussifeu",nomEvolutionPoussifeu);
        nomGrandEvolution.put("granivol",nomEvolutionGranivol);
        nomGrandEvolution.put("magneti",nomEvolutionMagneti);
        nomGrandEvolution.put("salameche",nomEvolutionSalameche);
        nomGrandEvolution.put("nidoran",nomEvolutionNidoran);
        nomGrandEvolution.put("grenousse",nomEvolutionGrenousse);

 */
    }

    public static int distance(Objet o1, Objet o2){
        int super_x;
        int super_y;

        super_x = Math.abs(o1.getX() - o2.getX());
        super_y = Math.abs(o1.getY() - o2.getY());

        return (int) Math.sqrt((super_x * super_x) + (super_y * super_y));
    }

}
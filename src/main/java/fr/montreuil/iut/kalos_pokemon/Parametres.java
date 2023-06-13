package fr.montreuil.iut.kalos_pokemon;

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
    public static final double pourcentageCoutAmelioration = 0.75;
    public static final double pourcentageRevente = 0.6;
    public static final int prixpoussifeu = 50;
    public static final int prixgranivol = 60;
    public static final int prixmagneti = 70;
    public static final int prixsalameche = 100;
    public static final int prixnidoran = 80;
    public static final int prixgrenousse = 75;
    public static final int prixelectrode = 100;
    public static final String nomEvolutionPoussifeu = "galifeu";
    public static final String nomEvolutionGranivol = "floravol";
    public static final String nomEvolutionMagneti = "magneton";
    public static final String nomEvolutionSalameche = "reptincel";
    public static final String nomEvolutionNidoran = "nidorino";
    public static final String nomEvolutionGrenousse = "croaporal";
    public static String cheminTerrains = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Terrain/";
    public static String cheminInterface = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Interface/acceuil/";
    public static String cheminIconeTour = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Sprites/Pokemon/";
    public static String cheminTirSprite = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Sprites/Tir/";

    //Parametres des chemin pour les tirs sprites


    //Parametres des Tours
    public static int tailleTuile = 32;
    public static final int offsetXTour = (tailleTourX - tailleTuile) / 2;
    public static final int offsetYTour = (tailleTourY - tailleTuile) / 2;
    public static int largeurTileset = 1056;
    public static int hauteurTileset = 512;
    public static int nbTuilesLargueur = largeurTileset / tailleTuile;
    public static int nbTuilesHauteur = hauteurTileset / tailleTuile;
    public static int idTuileTransparente = 15; //À modifier
    public static int debutZoneCheminTileSet = ((largeurTileset / tailleTuile / 2) + 1) * tailleTuile;
    public static int colonneZoneDepartTileSet = (largeurTileset / tailleTuile) - 3;
    public static int colonneZoneArriveeTileSet = (largeurTileset / tailleTuile) - 1;

    //map des images
    public static Map<String, Image> imagesTirMap = new HashMap<>();
    public static Map<String, Image> imagesPokemonMap = new HashMap<>();

    public static final String typeEau = "eau";
    public static final String typeFeu = "feu";
    public static final String typePlante = "plante";
    public static final String typeNeutre = "neutre";

    public static final double superEfficace = 1.3;
    public static final double peuEfficace = 0.7;

    //todo : à voir si classe à part
    public static final double affiniteType(String typeAttaquant, String typeDefenseur){
        if(typeAttaquant.equals(typeEau)){
            if(typeDefenseur.equals(typeEau)) return peuEfficace;
            else if (typeDefenseur.equals(typeFeu)) return superEfficace;
            else if (typeDefenseur.equals(typePlante)) return peuEfficace;
        } else if (typeAttaquant.equals(typeFeu)) {
            if(typeDefenseur.equals(typeEau)) return peuEfficace;
            else if (typeDefenseur.equals(typeFeu)) return peuEfficace;
            else if (typeDefenseur.equals(typePlante)) return superEfficace;
        }else if (typeAttaquant.equals(typePlante)){
            if(typeDefenseur.equals(typeEau)) return superEfficace;
            else if (typeDefenseur.equals(typeFeu)) return peuEfficace;
            else if (typeDefenseur.equals(typePlante)) return peuEfficace;
        }
        return 1;
    }

    public static final int prixTour(String nom) {
        if (nom.equals("poussifeu")) return prixpoussifeu;
        else if (nom.equals("granivol")) return prixgranivol;
        else if (nom.equals("magneti")) return prixmagneti;
        else if (nom.equals("salameche")) return prixsalameche;
        else if (nom.equals("nidoran")) return prixnidoran;
        else if (nom.equals("grenousse")) return prixgrenousse;
        else if (nom.equals("electrode")) return prixelectrode;
        return -1;
    }

    public static String nomPokemonBase(String nomTour){
        if(nomTour.equals("poussifeu") || nomTour.equals(nomEvolutionPoussifeu)) return "poussifeu";
        else if(nomTour.equals("granivol") || nomTour.equals(nomEvolutionGranivol)) return "granivol";
        else if(nomTour.equals("magneti") || nomTour.equals(nomEvolutionMagneti)) return "magneti";
        else if(nomTour.equals("salameche") || nomTour.equals(nomEvolutionSalameche)) return "salameche";
        else if(nomTour.equals("nidoran") || nomTour.equals(nomEvolutionNidoran)) return "nidoran";
        else if(nomTour.equals("grenousse") || nomTour.equals(nomEvolutionGrenousse)) return "grenousse";
        return null;
    }

    public static int coordonneesXYenCase(int ligne, int colonne, int largeur) {
        //int largeur = 32;//this.arrierePlan.get(0).size();
        return ligne * largeur + colonne;
    }

    public static int[] coordonneesCaseEnXY(int idCase, int largeur) {
        //int largeur = 32;//this.arrierePlan.get(0).size();
        //int[] xy = {idCase / largeur,idCase % largeur};
        return new int[]{idCase / largeur, idCase % largeur};
    }

    public static void chargeImage(){

        File directory = new File(cheminTirSprite);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Image i = new Image("file:" + cheminTirSprite + file.getName());
                imagesTirMap.put(file.getName(), i);
            }
        }

        directory = new File(cheminIconeTour);
        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Image i = new Image("file:" + cheminIconeTour + file.getName());
                imagesPokemonMap.put(file.getName(), i);
            }
        }
    }

    public static int distance(Objet o1, Objet o2){
        int super_x;
        int super_y;

        super_x = Math.abs(o1.getX() - o2.getX());
        super_y = Math.abs(o1.getY() - o2.getY());

        return (int) Math.sqrt((super_x * super_x) + (super_y * super_y));
    }

}
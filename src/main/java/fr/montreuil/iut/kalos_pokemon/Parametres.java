package fr.montreuil.iut.kalos_pokemon;

public class Parametres {
    public static final int tailleTourX = 45;
    public static final int tailleTourY = 45;
    public static final int prixpoussifeu = 50;
    public static final int prixgranivol = 60;
    public static final int prixmagneti = 70;
    public static final int prixsalameche = 100;
    public static final int prixvenalgue = 80;
    public static final int prixgrenousse = 75;
    public static final int prixelectrode = 100;
    public static String cheminTerrains = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Terrain/";
    public static String cheminIconeTour = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Sprites/Tour/";
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

    public static final int prixTour(String nom) {
        if (nom.equals("poussifeu")) return prixpoussifeu;
        else if (nom.equals("granivol")) return prixgranivol;
        else if (nom.equals("magneti")) return prixmagneti;
        else if (nom.equals("salameche")) return prixsalameche;
        else if (nom.equals("venalgue")) return prixvenalgue;
        else if (nom.equals("grenousse")) return prixgrenousse;
        else if (nom.equals("electrode")) return prixelectrode;
        return -1;
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

}
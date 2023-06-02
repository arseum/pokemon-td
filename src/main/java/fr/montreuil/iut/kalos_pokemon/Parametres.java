package fr.montreuil.iut.kalos_pokemon;

public class Parametres {
    public static String cheminTerrains = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Terrain/";
    public static String cheminIconeTour = "src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/Icone/Tour/";

    public static int tailleTuile = 32;
    public static int largeurTileset = 1056;
    public static int hauteurTileset = 512;
    public static int nbTuilesLargueur = largeurTileset / tailleTuile;
    public static int nbTuilesHauteur = hauteurTileset / tailleTuile;

    public static int idTuileTransparente = 15; //À modifier
    public static int debutZoneCheminTileSet = ((largeurTileset / tailleTuile / 2) + 1) * tailleTuile;

    public static int colonneZoneDepartTileSet = (largeurTileset / tailleTuile) - 3;
    public static int colonneZoneArriveeTileSet = (largeurTileset / tailleTuile) - 1;

    public static int vitesseMaximale = 10;

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
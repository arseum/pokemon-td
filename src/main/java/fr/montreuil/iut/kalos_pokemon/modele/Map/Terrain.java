package fr.montreuil.iut.kalos_pokemon.modele.Map;

import fr.montreuil.iut.kalos_pokemon.Parametres;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Terrain {

    private final ArrayList<ArrayList<Integer>> arrierePlan;
    private final ArrayList<ArrayList<Integer>> decor;
    private int[] caseDepart;
    private int idArrivee;
    private int[] caseArrivee;

    public Terrain() {
        this("default");
    }

    public Terrain(String nomTerrain) {
        arrierePlan = chargerCSV(nomTerrain);
        decor = chargerCSV(nomTerrain + "_decor");
        initCaseDepart();
        initCaseArrivee();
    }

    public ArrayList<ArrayList<Integer>> getArrierePlan() {
        return arrierePlan;
    }

    public ArrayList<ArrayList<Integer>> getDecor() {
        return decor;
    }


    /**
     * Vérifie si une case est un chemin
     *
     * @param ligne
     * @param colonne
     * @return
     */
    public boolean estChemin(int ligne, int colonne) {
        return (arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur) * Parametres.tailleTuile >= Parametres.debutZoneCheminTileSet;
    }

    public boolean estDecor(int ligne, int colonne) {
        return decor.get(ligne).get(colonne) != -1;
    }

    /**
     * Pour la création du terrain, retourne premiere ligne du fichier csv
     *
     * @param ligneLue
     * @return
     */
    private ArrayList<Integer> ligneTerrain(String[] ligneLue) {
        ArrayList<Integer> ligne = new ArrayList<>();
        for (int i = 0; i < ligneLue.length; i++) {
            ligne.add(Integer.parseInt(ligneLue[i]));
        }
        return ligne;
    }

    /**
     * Lit le fichier CSV et retourne sous forme de liste
     *
     * @param nomCSV
     * @return
     */
    private ArrayList<ArrayList<Integer>> chargerCSV(String nomCSV) {
        ArrayList<ArrayList<Integer>> terrainCharge = new ArrayList<>();
        try {
            File fichierCSV = new File(Parametres.cheminTerrains + nomCSV + ".csv");
            if (fichierCSV.exists()) {
                Scanner scanner = new Scanner(fichierCSV);
                while (scanner.hasNextLine()) {
                    String[] ligne = scanner.nextLine().split(",");
                    terrainCharge.add(ligneTerrain(ligne));
                }
            } else return null;
        } catch (FileNotFoundException e) {
            System.out.println("Une erreur lecture fichier");
        }
        return terrainCharge;
    }

    /*** BFS ***/

    public int coordonneesXYenCase(int ligne, int colonne) {
        int largeur = this.arrierePlan.get(0).size();
        return ligne * largeur + colonne;
    }

    public int[] coordonneesCaseEnXY(int idCase) {
        int largeur = this.arrierePlan.get(0).size();
        int[] xy = {idCase / largeur, idCase % largeur};
        return new int[]{idCase / largeur, idCase % largeur};
    }


    private void initCaseArrivee() {
        for (int ligne = 0; ligne < this.arrierePlan.size(); ligne++) {
            for (int colonne = 0; colonne < this.arrierePlan.get(0).size(); colonne++) {
                if (this.arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur == Parametres.colonneZoneArriveeTileSet) {
                    idArrivee = this.arrierePlan.get(0).size() * ligne + colonne;
                    caseArrivee = new int[]{colonne * 32, ligne * 32};
                }
            }
        }
    }

    private void initCaseDepart() {
        for (int ligne = 0; ligne < this.arrierePlan.size(); ligne++) {
            for (int colonne = 0; colonne < this.arrierePlan.get(0).size(); colonne++) {
                if (this.arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur == Parametres.colonneZoneDepartTileSet) {
                    //return this.arrierePlan.get(0).size() * ligne + colonne;
                    caseDepart = new int[]{colonne, ligne};
                }
            }
        }
    }

    public int[] getCaseDepart() {
        return caseDepart;
    }

    public int[] getCaseArrivee() {
        return caseArrivee;
    }

    public int getHauteurTerrain() {
        return this.arrierePlan.size() * Parametres.tailleTuile;
    }

    public int getLargeurTerrain() {
        return this.arrierePlan.get(0).size() * Parametres.tailleTuile;
    }

    public int getIdArrivee() {return idArrivee;}

    //public int getNbLigneTerrain() {return this.arrierePlan.size() * Parametres.tailleTuile;}

    //public int getLargeurTerrain() {return this.arrierePlan.get(0).size() * Parametres.tailleTuile;}

}
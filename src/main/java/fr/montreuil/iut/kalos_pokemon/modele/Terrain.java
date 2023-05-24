package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {

    private ArrayList<ArrayList<Integer>> arrierePlan;
    private ArrayList<ArrayList<Integer>> decor;

    public Terrain(){
        arrierePlan = chargerCSV("default");
        decor = chargerCSV("default_decor");
    }

    public Terrain(String nomTerrain){
        arrierePlan = chargerCSV(nomTerrain);
        decor = chargerCSV(nomTerrain + "_decor");
    }

    /*
    public int[][] getMap_test() {
        return map_test;
    }
    */

    public ArrayList<ArrayList<Integer>> getArrierePlan() {
        return arrierePlan;
    }

    public ArrayList<ArrayList<Integer>> getDecor() {
        return decor;
    }

    //Todo: Vérifier si correct (modulo)
    public boolean estChemin(int ligne, int colonne){
        return (arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur) * Parametres.tailleTuile >= Parametres.debutZoneCheminTileSet;
    }

    //Todo: Ajouter section pour tuile arrive et depart + methodes + parametre pour identifier

    private ArrayList<Integer> ligneTerrain(String[] ligneLue){
        ArrayList<Integer> ligne = new ArrayList<>();
        for(int i = 0; i < ligneLue.length; i++){
            ligne.add(Integer.parseInt(ligneLue[i]));
        }
        return ligne;
    }
    private ArrayList<ArrayList<Integer>> chargerCSV(String nomCSV){
        ArrayList<ArrayList<Integer>> terrainCharge = new ArrayList<>();
        try{
            File fichierCSV = new File(Parametres.cheminTerrains + nomCSV + ".csv");
            if(fichierCSV.exists()){
                Scanner scanner = new Scanner(fichierCSV);
                while (scanner.hasNextLine()){
                    String[] ligne = scanner.nextLine().split(",");
                    terrainCharge.add(ligneTerrain(ligne));
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Une erreur lecture fichier");
        }
        return terrainCharge;
    }


}
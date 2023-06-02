package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Terrain {

    private final ArrayList<ArrayList<Integer>> arrierePlan;
    private final ArrayList<ArrayList<Integer>> decor;

    public Terrain() {
        arrierePlan = chargerCSV("default");
        decor = chargerCSV("default_decor");
    }

    public Terrain(String nomTerrain) {
        arrierePlan = chargerCSV(nomTerrain);
        decor = chargerCSV(nomTerrain + "_decor");
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


    private int caseArrivee() {
        for (int ligne = 0; ligne < this.arrierePlan.size(); ligne++) {
            for (int colonne = 0; colonne < this.arrierePlan.get(0).size(); colonne++) {
                if (this.arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur == Parametres.colonneZoneArriveeTileSet) {
                    return this.arrierePlan.get(0).size() * ligne + colonne;
                }
            }
        }
        return -1;
    }

    public int[] caseDepart() {
        for (int ligne = 0; ligne < this.arrierePlan.size(); ligne++) {
            for (int colonne = 0; colonne < this.arrierePlan.get(0).size(); colonne++) {
                if (this.arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur == Parametres.colonneZoneDepartTileSet) {
                    //return this.arrierePlan.get(0).size() * ligne + colonne;
                    return new int[]{colonne, ligne};
                }
            }
        }
        return null;
    }

    //todo: faire un jUnit

    /**
     * Retourne les voisins d'une case; renvoi null si aucun voisins ou bien pas case non chemin
     *
     * @param idCase
     * @return
     */
    public ArrayList<Integer> adjacents(int idCase) {
        ArrayList<Integer> adjacents = new ArrayList<>();

        int largeur = this.arrierePlan.get(0).size();
        int hauteur = this.arrierePlan.size();

        int ligneCase = idCase / largeur;
        int colonneCase = idCase % largeur;

        //todo condition estChemin
        if (estChemin(ligneCase, colonneCase)) {
            int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

            for (int i = 0; i < direction.length; i++) {
                int nouvelleLigne = ligneCase + direction[i][0];
                int nouvelleColonne = colonneCase + direction[i][1];

                boolean ligneDsBords = (0 <= nouvelleLigne) && (nouvelleLigne <= hauteur - 1);
                boolean colonneDsBords = (0 <= nouvelleColonne) && (nouvelleColonne <= largeur - 1);

                if (ligneDsBords && colonneDsBords && estChemin(nouvelleLigne, nouvelleColonne)) {
                    adjacents.add(this.arrierePlan.get(0).size() * nouvelleLigne + nouvelleColonne);
                }
            }
        }

        return adjacents;
    }


    /**
     * Retourne une des map-liste possibles de chemin
     *
     * @return
     */
    public Map<Integer, Integer> algoBFS() {
        Map<Integer, Integer> arbreCouvrant = new HashMap<>();

        int caseArrivee = this.caseArrivee();
        ArrayList<Integer> parcours = new ArrayList<>();
        LinkedList<Integer> fifo = new LinkedList<>();

        arbreCouvrant.put(caseArrivee, null);
        fifo.addLast(caseArrivee);
        parcours.add(caseArrivee);

        while (!fifo.isEmpty()) {
            Integer caseActuelle = fifo.pollFirst();

            ArrayList<Integer> casesAdjacentes = this.adjacents(caseActuelle);
            Collections.shuffle(casesAdjacentes);

            for (Integer caseAdjacente : casesAdjacentes) {
                if (!parcours.contains(caseAdjacente)) {
                    fifo.addLast(caseAdjacente);
                    arbreCouvrant.put(caseAdjacente, caseActuelle);
                    parcours.add(caseAdjacente);
                }
            }
        }
        return arbreCouvrant;
    }

}
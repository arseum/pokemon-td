package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Terrain {

    private ArrayList<ArrayList<Integer>> arrierePlan;
    private ArrayList<ArrayList<Integer>> decor;
    //private Map<Integer, Integer> arbreCouvrant;

    public Terrain(){
        arrierePlan = chargerCSV("default");
        decor = chargerCSV("default_decor");

        //arbreCouvrant = new HashMap<>();
        //algoBFS();
    }

    public Terrain(String nomTerrain){
        arrierePlan = chargerCSV(nomTerrain);
        decor = chargerCSV(nomTerrain + "_decor");

        //arbreCouvrant = new HashMap<>();
        //algoBFS();
    }

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

    /*** BFS ***/

    //todo : convention 000 - 000 ou bien 63 et blabla
    /*
    private static int coordonneesXYenCase(int ligne, int colonne){
        int largeur = 32;//this.arrierePlan.get(0).size();
        //int largeur = this.arrierePlan.get(0).size();
        return ligne * largeur + colonne;
    }

    private static int[] coordonneesCaseEnXY(int idCase){
        int largeur = 32;//this.arrierePlan.get(0).size();
        //int[] xy = {idCase / largeur,idCase % largeur};
        return new int[] {idCase / largeur,idCase % largeur};
    }
    */
    public int coordonneesXYenCase(int ligne, int colonne){
        int largeur = this.arrierePlan.get(0).size();
        return ligne * largeur + colonne;
    }

    public int[] coordonneesCaseEnXY(int idCase){
        int largeur = this.arrierePlan.get(0).size();
        int[] xy = {idCase / largeur,idCase % largeur};
        return new int[] {idCase / largeur,idCase % largeur};
    }

    private int caseDepart(){
        return 10;
    }

    private int caseArrivee(){
        for (int ligne = 0; ligne < this.arrierePlan.size(); ligne ++){
            for (int colonne = 0 ; colonne < this.arrierePlan.get(0).size(); colonne++){
                if(this.arrierePlan.get(ligne).get(colonne) % Parametres.nbTuilesLargueur == Parametres.colonneZoneArriveeTileSet){
                    return this.arrierePlan.get(0).size() * ligne + colonne;
                }
            }
        }
        return -1;
    }

    //todo: faire un jUnit
    //public ArrayList<Integer> adjacents(int ligneCase, int colonneCase){
   public ArrayList<Integer> adjacents(int idCase){
        ArrayList<Integer> adjacents = new ArrayList<>();

        int largeur = this.arrierePlan.get(0).size();
        int hauteur = this.arrierePlan.size();

       int ligneCase = idCase / largeur;
       int colonneCase = idCase % largeur;
       //System.out.println(ligneCase + "," + colonneCase);

        //todo condition estChemin
        if(estChemin(ligneCase,colonneCase)){
            int[][] direction = {{-1,0},{0,-1},{1,0},{0,1}};

            for (int i = 0; i < direction.length; i++){
                int nouvelleLigne = ligneCase + direction[i][0];
                int nouvelleColonne = colonneCase + direction[i][1];

                boolean ligneDsBords = (0 <= nouvelleLigne) && (nouvelleLigne <= hauteur - 1);
                boolean colonneDsBords = (0 <= nouvelleColonne) && (nouvelleColonne <= largeur - 1);

                if(ligneDsBords && colonneDsBords && estChemin(nouvelleLigne,nouvelleColonne)){
                    //System.out.println("Ajouts d'adjacents " + nouvelleLigne + ", " + nouvelleColonne);
                    //adjacents.add(coordonneesXYenCase(nouvelleLigne,nouvelleColonne));

                    //ligne * largeur + colonne;
                    adjacents.add(this.arrierePlan.get(0).size() * nouvelleLigne + nouvelleColonne);
                }
            }
        }

       System.out.print("adjacents: ");
       System.out.println(adjacents);
        return adjacents;
    }


    //private void algoBFS(){
    public Map<Integer, Integer>  algoBFS() {
        Map<Integer, Integer> arbreCouvrant = new HashMap<>();

        int caseArrivee = this.caseArrivee();
        ArrayList<Integer> parcours = new ArrayList<>();
        LinkedList<Integer> fifo = new LinkedList<>();

        arbreCouvrant.put(caseArrivee, null);
        fifo.addLast(caseArrivee);
        parcours.add(caseArrivee);

        while (!fifo.isEmpty()){
            Integer caseActuelle = fifo.pollFirst();
            //System.out.println(adjacents());
            //System.out.println("algoBFS caseActuelle: " + caseActuelle);

            ArrayList<Integer> casesAdjacentes = this.adjacents(caseActuelle);
            Collections.shuffle(casesAdjacentes);

            for(Integer sommetAdjacent: casesAdjacentes){
                if(!parcours.contains(sommetAdjacent)){
                    fifo.addLast(sommetAdjacent);
                    arbreCouvrant.put(sommetAdjacent, caseActuelle);
                    parcours.add(sommetAdjacent);
                }
            }
        }
        return arbreCouvrant;
    }


    public static void main(String[] args) {
        Terrain t = new Terrain("bfs");
        System.out.println(t.caseArrivee());

        //System.out.println(t.adjacents(99));

        //System.out.println(t.arbreCouvrant);
        //System.out.println(t.arrierePlan);
        /*
        System.out.println(coordonneesXYenCase(1,31));

        ArrayList<ArrayList<Integer>> graphe = t.getArrierePlan();
        int largeur = graphe.get(0).size();
        int hauteur = graphe.size();
        System.out.println(graphe);
        System.out.println(largeur);
        System.out.println(hauteur);
        System.out.println(graphe.get(1).get(31));
        */
/*
        System.out.println(t.adjacents(0,0));
        System.out.println(t.adjacents(1,1));
        System.out.println(t.adjacents(0,31));
        System.out.println(t.adjacents(1,30));
        System.out.println(t.adjacents(6,4));
*/

        //for(int i = 0 ;)
    }

}
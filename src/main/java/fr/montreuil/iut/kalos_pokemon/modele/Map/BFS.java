package fr.montreuil.iut.kalos_pokemon.modele.Map;

import fr.montreuil.iut.kalos_pokemon.Parametres;

import java.util.*;

public class BFS {

    private static BFS uniqueInstance = null;
    private Terrain terrain;
    private BFS(Terrain terrain){
        this.terrain = terrain;
    }

    public static BFS getBFS(Terrain terrain){
        if(uniqueInstance == null){uniqueInstance = new BFS(terrain);}
        return uniqueInstance;
    }

    public static void resetBFS(){uniqueInstance = null;};

    private void ajouteAdjacents(ArrayList adjacents, boolean estTerrestre, int idCase, int hauteur, int largeur){
        int ligneCase = idCase / largeur;
        int colonneCase = idCase % largeur;

        int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int i = 0; i < direction.length; i++) {
            int nouvelleLigne = ligneCase + direction[i][0];
            int nouvelleColonne = colonneCase + direction[i][1];

            boolean ligneDsBords = (0 <= nouvelleLigne) && (nouvelleLigne <= hauteur - 1);
            boolean colonneDsBords = (0 <= nouvelleColonne) && (nouvelleColonne <= largeur - 1);

            if(ligneDsBords && colonneDsBords ){
                if(estTerrestre && this.terrain.estChemin(nouvelleLigne, nouvelleColonne)){
                    //adjacents.add(this.arrierePlan.get(0).size() * nouvelleLigne + nouvelleColonne);
                    adjacents.add((this.terrain.getLargeurTerrain()/Parametres.tailleTuile)  * nouvelleLigne + nouvelleColonne);
                }
                else if(!estTerrestre) {
                    //adjacents.add(this.arrierePlan.get(0).size() * nouvelleLigne + nouvelleColonne);
                    adjacents.add((this.terrain.getLargeurTerrain()/Parametres.tailleTuile)  * nouvelleLigne + nouvelleColonne);
                }
            }
        }
    }

    /**
     * Retourne les voisins d'une case; renvoi null si aucun voisins ou bien pas case non chemin
     *
     * @param idCase
     * @return
     */
    private ArrayList<Integer> adjacents(int idCase, boolean estTerrestre) {
        ArrayList<Integer> adjacents = new ArrayList<>();

        //int largeur = this.arrierePlan.get(0).size();
        int largeur = this.terrain.getLargeurTerrain()/Parametres.tailleTuile;
        //int hauteur = this.arrierePlan.size();
        int hauteur = this.terrain.getHauteurTerrain()/Parametres.tailleTuile;

        int ligneCase = idCase / largeur;
        int colonneCase = idCase % largeur;

        if (this.terrain.estChemin(ligneCase, colonneCase) && estTerrestre) {
            ajouteAdjacents(adjacents,true, idCase, hauteur, largeur);
        }
        else {
            ajouteAdjacents(adjacents,false, idCase, hauteur, largeur);
        }

        return adjacents;
    }


    /**
     * Retourne une des map-liste possibles de chemin
     *
     * @return
     */
    public Map<Integer, Integer> algoBFS(boolean estTerrestre) {
        Map<Integer, Integer> arbreCouvrant = new HashMap<>();

        ArrayList<Integer> parcours = new ArrayList<>();
        LinkedList<Integer> fifo = new LinkedList<>();

        //arbreCouvrant.put(idArrivee, null);
        //fifo.addLast(idArrivee);
        //parcours.add(idArrivee);

        arbreCouvrant.put(this.terrain.getIdArrivee(), null);
        fifo.addLast(this.terrain.getIdArrivee());
        parcours.add(this.terrain.getIdArrivee());

        while (!fifo.isEmpty()) {
            Integer caseActuelle = fifo.pollFirst();

            ArrayList<Integer> casesAdjacentes = this.adjacents(caseActuelle, estTerrestre);
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

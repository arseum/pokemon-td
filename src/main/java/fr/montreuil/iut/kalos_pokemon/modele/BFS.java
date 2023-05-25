package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


public class BFS {

    private Terrain t;
    private ArrayList<ArrayList<Integer>> g;
    private Integer sommetArrive;
    private ArrayList<Integer> parcours;
    private Map<Integer, Integer> predecesseurs;

    private void algoBFS() {
        LinkedList<Integer> fifo = new LinkedList<>();
        this.predecesseurs.put(sommetArrive, null);
        fifo.addLast(sommetArrive);

        parcours.add(sommetArrive);

        while (!fifo.isEmpty()){
            Integer sommetActuel = fifo.pollFirst();

            for(Integer sommetAdjacent: t.adjacents(sommetArrive)){
                if(!this.parcours.contains(sommetAdjacent)){
                    fifo.addLast(sommetAdjacent);
                    this.predecesseurs.put(sommetAdjacent, sommetActuel);
                    parcours.add(sommetAdjacent);
                }
            }
        }
    }

}

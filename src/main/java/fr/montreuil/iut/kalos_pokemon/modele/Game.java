package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Game {

    private Terrain terrain;
    /**
     * il faut que cette liste contiene tout les ennemie en fonction du - loin au + loin
     * pour l'instant tout els togepi on la meme vitesse donc ce n'est pas compliqué mais par a suite il faudra envisager
     * le fait que les ennemi peuvent se depacer les uns des autres
     */
    private ObservableList<Ennemi> listEnnemi;
    private ObservableList<Tour> listTour;
    private IntegerProperty pokedollar;

    public Game() {
        terrain = new Terrain();
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public IntegerProperty PokedollarProperty() {
        return pokedollar;
    }

    public int getPokedollar() {
        return pokedollar.get();
    }

    public void ajoutePokedollar(int value) {
        pokedollar.setValue(pokedollar.get() + value);
    }

    public void ajouteEnnemi(Ennemi e) {
        listEnnemi.add(e);
        e.setGame(this);
    }

    public void ajouteTour(Tour t){
        listTour.add(t);
        t.setGame(this);
    }

    public ObservableList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }
    public ObservableList<Tour> getListTour() { return listTour; }

    /**
     * methode appeler a chaque frame
     * utilisé notament pour les deplacements
     */
    public void deplacment() {

        for (Ennemi e : listEnnemi) {
            e.seDeplace();
        }

    }

    /**
     * methode appeler toutes les 30 frames (soit 1/2s )
     * utilisé pour faire des tests sur la mort des pokemons
     */
    public void demiSeconde() {

        for (Ennemi e : listEnnemi) {
            e.diminueHP(5);
        }

        for (int i = listEnnemi.size() -1 ; i >= 0 ; i-- ){
            if (listEnnemi.get(i).getHp() <= 0){
                //donne de l'argent au joueur
                this.ajoutePokedollar(listEnnemi.get(i).getRecompense());

                //supprime le sprite du pokemon

                //puis on le supprime
                listEnnemi.remove(i);
            }
        }
    }

}

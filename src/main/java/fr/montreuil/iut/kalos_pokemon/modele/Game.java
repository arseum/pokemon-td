package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Game {

    private final Terrain terrain;
    /**
     * il faut que cette liste contiene tout les ennemie en fonction du - loin au + loin
     * pour l'instant tout els togepi on la meme vitesse donc ce n'est pas compliqué mais par a suite il faudra envisager
     * le fait que les ennemi peuvent se depacer les uns des autres
     */
    private final ObservableList<Ennemi> listEnnemi;
    private final ObservableList<Attaque> listProjectile;
    private final ObservableList<Tour> listTour;
    private final IntegerProperty pokedollar;

    public Game() {
        terrain = new Terrain();
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
    }

    public Game(String nomTerrain) {
        //todo terrain_BFS 2
        terrain = new Terrain(nomTerrain);
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
    }

    //todo 1

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
        this.listEnnemi.add(e);
    }

    public void ajouteTour(Tour t) {
        listTour.add(t);
        t.setGame(this);
    }

    public void ajouteProjectile(Attaque a) {
        listProjectile.add(a);
    }

    public ObservableList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }

    public ObservableList<Tour> getListTour() {
        return listTour;
    }

    public ObservableList<Attaque> getListProjectile() {
        return listProjectile;
    }

    public void remove(Projectile p) {
        listProjectile.remove(p);
    }

    public void remove(Ennemi e) {
        listEnnemi.remove(e);
    }

    /**
     * methode appelée a chaque frame
     * utilisé notament pour les deplacements
     */
    public void deplacment() {

        for (Ennemi e : listEnnemi) {
            e.seDeplace();
        }

        for (int i = listProjectile.size() - 1; i >= 0; i--) {
            if (listProjectile.get(i) instanceof Zone && ((Zone) listProjectile.get(i)).isActif())
                listProjectile.get(i).bouge();
            else
                listProjectile.get(i).bouge();
        }

    }

    /**
     * methode appelée toutes les 30 frames (soit 1/2s )
     * utilisé pour faire des tests sur la mort des pokemons
     */
    public void demiSeconde() {

        for (Tour t : listTour) {
            t.attaque();
        }
    }

    public Ennemi chercheEnnemi(String id) {

        Ennemi e = null;
        int index = 0;

        while (e == null && index < listEnnemi.size()) {
            if (Objects.equals(listEnnemi.get(index).getId(), id))
                e = listEnnemi.get(index);
            else
                index++;
        }

        return e;
    }

}

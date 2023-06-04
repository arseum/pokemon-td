package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Venalgue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Game {

    private final Terrain terrain;
    /**
     * il faut que cette liste contiene tout les ennemie en fonction du - loin au + loin
     * pour l'instant tout les togepi on la meme vitesse donc ce n'est pas compliqué mais par a suite il faudra envisager
     * le fait que les ennemi peuvent se depacer les uns des autres
     */
    private final ObservableList<Ennemi> listEnnemi;
    private final ObservableList<Attaque> listProjectile;
    private final ObservableList<Tour> listTour;
    private final IntegerProperty pokedollar;
    private IntegerProperty nbFrame;

    public Game() {
        terrain = new Terrain();
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
        nbFrame = new SimpleIntegerProperty(0);
    }

    public Game(String nomTerrain) {
        //todo terrain_BFS 2
        terrain = new Terrain(nomTerrain);
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
        nbFrame = new SimpleIntegerProperty(0);
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

    public IntegerProperty nbFrameProperty() {
        return nbFrame;
    }

    public int getNbFrame() {
        return nbFrame.get();
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

    public void remove(Ennemi e) { listEnnemi.remove(e);
    }

    /**
     * methode appelée a chaque frame
     * utilisé notament pour les deplacements
     */
    public void uneFrame() {

        for (Ennemi e : listEnnemi) {
            e.seDeplace();
        }

        for (int i = listProjectile.size() - 1; i >= 0; i--) {
            if (listProjectile.get(i) instanceof Zone && ((Zone) listProjectile.get(i)).isActif())
                listProjectile.get(i).bouge();
            else
                listProjectile.get(i).bouge();
        }

        for (Tour t : listTour) {
            if (getNbFrame() >= t.tempProchaineAttaque)
                t.attaque();
            if (t instanceof Venalgue && getNbFrame() % 20 == 0)
                ((Venalgue) t).apliquePoison();
        }

    }

}

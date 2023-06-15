package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Nidoran;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private final IntegerProperty vie;

    private Wave vague;



    public Game(String nomTerrain) {
        //todo terrain_BFS 2
        terrain = new Terrain(nomTerrain);
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
        nbFrame = new SimpleIntegerProperty(0);
        vie = new SimpleIntegerProperty(15);
        vague= new Wave(terrain,this);
        vague.nbFrameProperty().bind(nbFrame);
    }

    public Game() {
        this("default");
    }

    public Wave getVague() {
        return vague;
    }
    public Terrain getTerrain() {
        return terrain;
    }
    public IntegerProperty PokedollarProperty() {
        return pokedollar;
    }

    public int getVie() {
        return vie.get();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }

    public int getPokedollar() {
        return pokedollar.get();
    }

    public IntegerProperty nbFrameProperty() {
        return nbFrame;
    }

    public int getNbFrameValue() {
        return nbFrame.get();
    }

    public IntegerProperty getNbFrame(){return  nbFrame;}

    public void ajoutePokedollar(int value) {
        pokedollar.setValue(pokedollar.get() + value);
    }

    public void perdVie(int value) {
        vie.set(vie.get() - value);
    }

    public void ajouteEnnemi(Ennemi e) {
        this.listEnnemi.add(e);
    }
    public void ajouteTour(Tour t) {
        if (tourAchetable(t)) {
            listTour.add(t);
            t.setGame(this);
            pokedollar.set(pokedollar.get() - t.getPrix());
        }
    }
    public void depensePokedollar(int value){ pokedollar.set(pokedollar.get() - value);}

    public void ajouteProjectile(Attaque a) {
        listProjectile.add(a);
    }

    public ObservableList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }

    public boolean tourSurMemePosition(int x, int y) {
        for (Tour t : this.getListTour()) {
            if (t.getX() / Parametres.tailleTuile == x / Parametres.tailleTuile && t.getY() / Parametres.tailleTuile == y / Parametres.tailleTuile) {
                return true;
            }

        }
        return false;
    }

    public boolean tourAchetable(Tour t) {
        return t.getPrix() <= this.pokedollar.get();
    }

    public boolean tourAchetable(String nomTour) {
        return (Parametres.prixTour(nomTour) != -1) && (Parametres.prixTour(nomTour) <= this.pokedollar.get());
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
    public void uneFrame() {

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            listEnnemi.get(i).seDeplace();
        }

        for (int i = listProjectile.size() - 1; i >= 0; i--) {
            //les zones douvent bouger que lorsqu'elles sont actifs car elle sont en permanance dans la list de projectiles
            if (listProjectile.get(i) instanceof Zone && ((Zone) listProjectile.get(i)).isActif())
                listProjectile.get(i).bouge();
            else
                listProjectile.get(i).bouge();
        }

        for (Tour t : listTour) {
            if (getNbFrameValue() >= t.tempProchaineAttaque)
                t.attaque();
            if (t instanceof Nidoran && getNbFrameValue() % 20 == 0)
                ((Nidoran) t).apliquePoison();
        }

    }

    public Tour retourneTourAPartirId(String id){
        for (Tour t : this.listTour){
            if(t.getId().equals(id)) return t;
        }
        return null;
    }

    public void vendreTour(Tour t){
        this.listTour.remove(t);
        this.pokedollar.setValue(this.pokedollar.getValue() + t.prixRevente());
    }

    public void ameliorerTour(Tour t){
        if(this.pokedollar.getValue() - t.prixAmelioration() >= 0 && t.getLevel() < Parametres.niveauEvolutionTour){
            this.pokedollar.setValue(this.pokedollar.getValue() - t.prixAmelioration());
            t.levelUp();
        }
    }




}

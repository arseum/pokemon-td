package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Magneti;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Nidoran;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Game {

    private final Terrain terrain;
    /**
     * contient tout les ennemie en vie sur la map
     */
    private final ObservableList<Ennemi> listEnnemi;
    /**
     * contient tout les projectiles qui sont en train de se diriger vers un ennemie
     */
    private final ObservableList<Attaque> listProjectile;
    /**
     * contient toutes les tours qui sont posé
     */
    private final ObservableList<Tour> listTour;
    private final IntegerProperty pokedollar;
    private IntegerProperty nbFrame;
    private final IntegerProperty vie;
    private Wave vague;
    private boolean bossVaincu = false;

    public Game(String nomTerrain) {
        terrain = new Terrain(nomTerrain);
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(850);
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

    public void setBossVaincu(boolean bossVaincu) {
        this.bossVaincu = bossVaincu;
    }
    public boolean bossEstVaincu() {
        return bossVaincu;
    }

    public IntegerProperty vieProperty() {
        return vie;
    }

    public int getPokedollar() {
        return pokedollar.get();
    }

    public ObservableList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }

    public IntegerProperty nbFrameProperty() {
        return nbFrame;
    }

    public int getNbFrameValue() {
        return nbFrame.get();
    }
    public ObservableList<Tour> getListTour() {
        return listTour;
    }

    public ObservableList<Attaque> getListProjectile() {
        return listProjectile;
    }

    public IntegerProperty getNbFrame(){return  nbFrame;}
    public void perdVie(int value) {
        vie.set(vie.get() - value);
    }
    public void ajoutePokedollar(int value) {
        pokedollar.setValue(pokedollar.get() + value);
    }
    public void ajouteProjectile(Attaque a) {
        listProjectile.add(a);
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

    public boolean tourSurMemePosition(int x, int y) {
        for (Tour tour : this.getListTour()) {
            if (estSurMemeCase(tour,x,y)) {
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

    public void remove(Attaque p) {
        listProjectile.remove(p);
    }

    public void remove(Ennemi e) {
        listEnnemi.remove(e);
    }

    /**
     * methode appelée a chaque frame
     * utilisé notament pour les deplacements et la gestion des tours
     */
    public void uneFrame() {

        deplacement(listEnnemi);
        deplacement(listProjectile);
        gestionTour();

    }

    public Tour retourneTourAPartirId(String id){
        for (Tour t : this.listTour){
            if(t.getId().equals(id)) return t;
        }
        return null;
    }

    /**
     * supprime une tour de la liste et rajoute un peu d'argent en contre-partit
     */
    public void vendreTour(Tour t){
        this.listTour.remove(t);
        if (t instanceof Magneti magneti)
            magneti.vendre();
        ajoutePokedollar(t.prixRevente());
    }

    public void ameliorerTour(Tour t){
        if(peutEtreAmeliorer(t)){
            ajoutePokedollar(-t.prixAmelioration());
            t.levelUp();
        }
    }

    private boolean peutEtreAmeliorer(Tour tour) {
        return this.pokedollar.getValue() - tour.prixAmelioration() >= 0 && tour.getLevel() < Parametres.niveauEvolutionTour;
    }

    private void deplacement(ObservableList<? extends Mobile> list){
        for (int i = list.size() - 1; i >= 0; i--)
            list.get(i).bouge();
    }

    private void gestionTour() {

        for (Tour t : listTour) {
            //il faut avoir que t.attaque() ici il faut donc que le apliquePoison et le zone.bouge soit coder dans une classe
            // apart qui sera strategie de ataque
            if (getNbFrameValue() >= t.tempProchaineAttaque)
                t.attaque();

            if (t instanceof Nidoran nidoran && getNbFrameValue() % 20 == 0)
                nidoran.apliquePoison();
            else if (t instanceof Magneti magneti && magneti.isActif())
                magneti.getZone().bouge();
        }
    }

    private boolean estSurMemeCase(Tour t, int x, int y) {
        return t.getX() / Parametres.tailleTuile == x / Parametres.tailleTuile && t.getY() / Parametres.tailleTuile == y / Parametres.tailleTuile;
    }

}

package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Map.GestionnaireVagues;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Game {

    private final Terrain terrain;
    //contient tout les ennemie en vie sur la map
    private final ObservableList<Ennemi> listEnnemi;
    //contient tout les projectiles qui sont en train de se diriger vers un ennemi
    private final ObservableList<Projectile> listeProjectile;
    //contient toutes les tours qui sont posées
    private final ObservableList<Tour> listTour;
    private final IntegerProperty pokedollar;
    private IntegerProperty nbFrame;
    private final IntegerProperty vie;
    private GestionnaireVagues vague;
    private boolean bossVaincu = false;
    private static Game uniqueInstanceGame = null;
    private static String nomTerrain = null;

    private Game(String nomTerrain) {
        terrain = new Terrain(nomTerrain);
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(Parametres.argentDepartPourDev);
        nbFrame = new SimpleIntegerProperty(0);
        vie = new SimpleIntegerProperty(15);
        vague = new GestionnaireVagues(terrain);
        vague.nbFrameProperty().bind(nbFrame);
        listeProjectile = FXCollections.observableArrayList();
    }

    public GestionnaireVagues getVague() {
        return vague;
    }

    public static Game getGame() {
        if (nomTerrain != null) {
            Game.nomTerrain = "default";
        }
        return getGame(Game.nomTerrain);
    }

    public static void resetGame() {
        uniqueInstanceGame = null;
        nomTerrain = null;
    }

    public void perdVie(int value) {
        vie.set(vie.get() - value);
    }

    /**
     * methode appelée a chaque frame
     * utilisé notament pour les deplacements et la gestion des tours
     */
    public void uneFrame() {
        deplacementProjectile(listeProjectile);
        gestionTour();
        gestionEnnemi();
    }

    private void gestionEnnemi() {
        Ennemi e;
        deplacement(listEnnemi);
        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            e = listEnnemi.get(i);
            if (e.isEstArrive())
                listEnnemi.remove(e);
            else
                e.gereEffet();
        }
    }

    private void deplacement(ObservableList<? extends Mobile> list) {
        for (int i = list.size() - 1; i >= 0; i--)
            list.get(i).bouge();
    }

    private void deplacementProjectile(ObservableList<? extends Projectile> list) {
        for (int i = list.size() - 1; i >= 0; i--)
            list.get(i).bouge();
    }

    //Tours
    public Tour retourneTourAPartirId(String id) {
        for (Tour t : this.listTour) {
            if (t.getId().equals(id)) return t;
        }
        return null;
    }

    public void vendreTour(Tour t) {
        this.listTour.remove(t);
        ajoutePokedollar(t.prixRevente());
    }

    public void ameliorerTour(Tour t) {
        if (peutEtreAmeliorer(t)) {
            ajoutePokedollar(-t.prixAmelioration());
            t.levelUp();
        }
    }

    private void gestionTour() {
        for (Tour t : listTour) {
            if (getNbFrameValue() >= t.getTempProchaineAttaque())
                t.attaque();
        }
    }

    private boolean peutEtreAmeliorer(Tour tour) {
        return this.pokedollar.getValue() - tour.prixAmelioration() >= 0 && tour.getLevel() < Parametres.niveauEvolutionTour;
    }

    public void ajouteTour(Tour t) {
        if (tourAchetable(t)) {
            listTour.add(t);
            pokedollar.set(pokedollar.get() - t.getPrix());
        }
    }

    public boolean tourSurMemePosition(int x, int y) {
        for (Tour tour : this.getListTour()) {
            if (estSurMemeCase(tour, x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean tourAchetable(Tour t) {
        return t.getPrix() <= this.pokedollar.get();
    }

    public boolean tourAchetable(String nomTour) {
        return (PokemonEnum.valueOf(nomTour).getPrix() <= this.pokedollar.get());
    }

    private boolean estSurMemeCase(Tour t, int x, int y) {
        return t.getX() / Parametres.tailleTuile == x / Parametres.tailleTuile && t.getY() / Parametres.tailleTuile == y / Parametres.tailleTuile;
    }

    public void ajoutePokedollar(int value) {
        pokedollar.setValue(pokedollar.get() + value);
    }

    public void ajouteProjectile(Projectile p) {
        listeProjectile.add(p);
    }

    public void ajouteEnnemi(Ennemi e) {
        this.listEnnemi.add(e);
    }

    public void removeProjectile(Projectile p) {
        listeProjectile.remove(p);
    }

    public void removeEnnemi(Ennemi e) {
        listEnnemi.remove(e);
    }

    /**
     * SETTERS, GETTERS & PROPERTIES
     **/

    public ObservableList<Projectile> getListeProjectile() {
        return listeProjectile;
    }

    public IntegerProperty PokedollarProperty() {
        return pokedollar;
    }

    public void setBossVaincu(boolean bossVaincu) {
        this.bossVaincu = bossVaincu;
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

    public IntegerProperty getNbFrame() {
        return nbFrame;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public static Game getGame(String nomTerrain) {
        if (uniqueInstanceGame == null) {
            Game.nomTerrain = nomTerrain;
            uniqueInstanceGame = new Game(Game.nomTerrain);
        }
        return uniqueInstanceGame;
    }
}

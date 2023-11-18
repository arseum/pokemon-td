package fr.montreuil.iut.kalos_pokemon.Controleur;

import fr.montreuil.iut.kalos_pokemon.Donnees.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Permet de gérer l'affichage du stackpane crée par ObsClicMenuAchatTour.
 * Le stackpane suit le mouvement de la souris: mouvement discret sur la map et mouvement hors map.
 * Si la position du stackpane correspond à un endroit où la tour ne peut être place l'image sera grisée
 * Lorsque on clique sur un endroit de la map plaçable, la tour est ajoutée au modele
 */
public class ObsMvtClicAjoutTour implements EventHandler<MouseEvent> {

    private final ObsClicMenuAchatTour obsClic;
    private final SimpleDoubleProperty xTour;
    private final SimpleDoubleProperty yTour;
    private final SimpleBooleanProperty estDansTerrainX;
    private final SimpleBooleanProperty estDansTerrainY;
    private final Game game;

    public ObsMvtClicAjoutTour(ObsClicMenuAchatTour obsClic, Game game) {
        //paneTerrain.setCursor(Cursor.MOVE);
        this.obsClic = obsClic;
        this.xTour = new SimpleDoubleProperty(0);
        this.yTour = new SimpleDoubleProperty(0);
        this.estDansTerrainX = new SimpleBooleanProperty();
        this.estDansTerrainY = new SimpleBooleanProperty();
        this.game = game;

        this.obsClic.imageTour.layoutXProperty().bind(xTour);
        this.obsClic.imageTour.layoutYProperty().bind(yTour);

        this.estDansTerrainX.bind(xTour.greaterThan(0).and(xTour.lessThan(game.getTerrain().getLargeurTerrain())));
        this.estDansTerrainY.bind(yTour.greaterThan(0).and(yTour.lessThan(game.getTerrain().getHauteurTerrain())));

    }

    private boolean estPlacable(int x, int y) {
        int width = game.getTerrain().getArrierePlan().get(0).size();
        int height = game.getTerrain().getArrierePlan().size();
        if (0 <= x && x < width && 0 <= y && y < height) {
            return !game.getTerrain().estChemin(y, x) && !game.getTerrain().estDecor(y, x) && !game.tourSurMemePosition(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
        } else return false;
    }

    private int divisionEuclidienne(double nombre) {
        return (int) nombre / Parametres.tailleTuile;
    }

    /**
     * Niveau modele place la tour niveau coin sup gauche, par exemple (0,0) ou bien (32,32)
     * Le sprite a les memes coordonnes - le offset
     *
     * @param mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {


        if (mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED) {
            if (estDansTerrainX.get() && estDansTerrainY.get()) {
                xTour.set(divisionEuclidienne(mouseEvent.getX()) * Parametres.tailleTuile + Parametres.tailleTourX / 2);
                yTour.set(divisionEuclidienne(mouseEvent.getY()) * Parametres.tailleTuile + Parametres.tailleTourY / 2);
            } else {
                xTour.set(mouseEvent.getX());
                yTour.set(mouseEvent.getY());
            }
            if (!estPlacable(divisionEuclidienne(xTour.get()), divisionEuclidienne(yTour.get()))) {
                this.obsClic.imageTour.lookup("#grise").toFront();
            } else {
                this.obsClic.imageTour.lookup("#normal").toFront();
            }
        }
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            int x = divisionEuclidienne(xTour.get() - Parametres.tailleTourX / 2);
            int y = divisionEuclidienne(yTour.get() - Parametres.tailleTourY / 2);
            if (this.obsClic.estSelectionnee.getValue() && estPlacable(x, y)) {
                if (this.obsClic.tourSelectionnee.equals(PokemonEnum.poussifeu.name())) {
                    Tour t;
                    t = new Poussifeu(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
                    game.ajouteTour(t);
                } else if (this.obsClic.tourSelectionnee.equals(PokemonEnum.salameche.name())) {
                    Tour t;
                    t = new Salameche(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
                    game.ajouteTour(t);
                } else if (this.obsClic.tourSelectionnee.equals((PokemonEnum.magneti.name()))) {
                    Tour t;
                    t = new Magneti(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
                    game.ajouteTour(t);
                } else if (this.obsClic.tourSelectionnee.equals(PokemonEnum.nidoran.name())) {
                    Tour t;
                    t = new Nidoran(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
                    game.ajouteTour(t);
                } else if (this.obsClic.tourSelectionnee.equals(PokemonEnum.grenousse.name())) {
                    Tour t;
                    t = new Grenousse(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
                    game.ajouteTour(t);
                }else if (this.obsClic.tourSelectionnee.equals(PokemonEnum.granivol.name())) {
                    Tour t;
                    t = new Granivol(x * Parametres.tailleTuile, y * Parametres.tailleTuile);
                    game.ajouteTour(t);
                }
                this.obsClic.supprimeImage();
                this.obsClic.estSelectionnee.setValue(false);

            }
        }

    }
}

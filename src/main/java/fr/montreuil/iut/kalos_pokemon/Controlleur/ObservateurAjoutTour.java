package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Poussifeu;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Salameche;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ObservateurAjoutTour implements EventHandler<MouseEvent> {

    private ObservateurMenuTourClic obsClic;
    private Pane paneTerrain;

    private SimpleDoubleProperty xTour;
    private SimpleDoubleProperty yTour;

    private SimpleBooleanProperty estDansTerrainX;
    private SimpleBooleanProperty estDansTerrainY;

    private Game game;

    public ObservateurAjoutTour(ObservateurMenuTourClic obsClic, Pane paneTerrain, Game game) {
        this.obsClic = obsClic;
        this.paneTerrain = paneTerrain;
        this.xTour = new SimpleDoubleProperty(0);
        this.yTour = new SimpleDoubleProperty(0);
        this.estDansTerrainX = new SimpleBooleanProperty();
        this.estDansTerrainY = new SimpleBooleanProperty();
        this.game = game;

        this.obsClic.imageTour.layoutXProperty().bind(xTour);
        this.obsClic.imageTour.layoutYProperty().bind(yTour);

        //todo: On peut pas recup pane.getHeight()
        this.estDansTerrainX.bind(xTour.greaterThan(0).and(xTour.lessThan(999)));
        this.estDansTerrainY.bind(yTour.greaterThan(0).and(yTour.lessThan(480)));

    }

    private boolean estPlacable(int x, int y){
        int width = game.getTerrain().getArrierePlan().get(0).size() ;
        int height = game.getTerrain().getArrierePlan().size() ;
        if(0 <= x && x < width && 0 <= y && y < height){
            return !game.getTerrain().estChemin(y,x)&&!game.getTerrain().estDecor(y,x);
        }
        else return false;
    }

    private int divisionEuclidienne(double nombre){
        return (int)nombre / Parametres.tailleTuile;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED) {
            //System.out.print(this.obsClic.imageTour.getLayoutX());
            //System.out.println(", " + this.xTour.get());

            if (estDansTerrainX.get() && estDansTerrainY.get()) {
                xTour.set(divisionEuclidienne(mouseEvent.getX()) * Parametres.tailleTuile );
                yTour.set(divisionEuclidienne(mouseEvent.getY()) * Parametres.tailleTuile );
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
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED){

            //todo condition tour meme endroit

            int x = divisionEuclidienne(xTour.get());
            int y = divisionEuclidienne(yTour.get());
            if(this.obsClic.estSelectionnee && estPlacable(x,y)){
                if(this.obsClic.tourSelectionnee.equals("poussifeu")){
                    game.ajouteTour(new Poussifeu(x * Parametres.tailleTuile, y * Parametres.tailleTuile, game));
                }
                else if (this.obsClic.tourSelectionnee.equals("salameche")) {
                    game.ajouteTour(new Salameche(x * Parametres.tailleTuile, y * Parametres.tailleTuile, game));
                }
                this.obsClic.supprimeImage();
                this.obsClic.estSelectionnee = false;

            }
        }

    }
}

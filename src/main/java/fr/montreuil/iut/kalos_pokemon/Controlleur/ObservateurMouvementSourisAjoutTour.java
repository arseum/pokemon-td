package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ObservateurMouvementSourisAjoutTour implements EventHandler<MouseEvent> {

    private ObservateurClicSelectionTour obsClic;
    private Pane paneTerrain;

    private SimpleDoubleProperty xTour;
    private SimpleDoubleProperty yTour;

    private SimpleBooleanProperty estDansTerrainX;
    private SimpleBooleanProperty estDansTerrainY;

    private Game game;

    //private ImageView im;

    public ObservateurMouvementSourisAjoutTour(ObservateurClicSelectionTour obsClic, Pane paneTerrain, Game game) {
        this.obsClic = obsClic;
        this.paneTerrain = paneTerrain;
        this.xTour = new SimpleDoubleProperty(0);
        this.yTour = new SimpleDoubleProperty(0);
        this.estDansTerrainX = new SimpleBooleanProperty();
        this.estDansTerrainY = new SimpleBooleanProperty();
        this.game = game;

        //this.obsClic.imageTour.getChildren().get(1).toFront();

        this.obsClic.imageTour.layoutXProperty().bind(xTour);
        this.obsClic.imageTour.layoutYProperty().bind(yTour);
        this.estDansTerrainX.bind(xTour.greaterThan(0).and(xTour.lessThan(500)));
        this.estDansTerrainY.bind(yTour.greaterThan(0).and(yTour.lessThan(500)));

    }

    private boolean estPlacable(int x, int y){
        int width = game.getTerrain().getArrierePlan().get(0).size() - 1;
        int height = game.getTerrain().getArrierePlan().size() - 1;
        System.out.println(width + ", " + height);
        if(0 < x && x < width && 0 < y && y < height){
            return !game.getTerrain().estChemin(y,x)&&!game.getTerrain().estDecor(y,x);
        }
        else return false;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = (int)mouseEvent.getX()/32;
        int y = (int)mouseEvent.getY()/32;
        xTour.set(x*32 - 6);
        yTour.set(y*32 - 6);
        this.obsClic.imageTour.setVisible(estDansTerrainX.get() && estDansTerrainY.get());
        System.out.println(xTour.get() + ", " + yTour.get());
        if(!estPlacable(x,y)){
            System.out.println("non placeable");
            this.obsClic.imageTour.lookup("#grise").toFront();
        }
        else {
            this.obsClic.imageTour.lookup("#normal").toFront();
        }


        /*
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED_TARGET){
            System.out.println("Est entre");

        } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
            System.out.println("Est sortie");
        }

         */
        /*
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED){
            paneTerrain.getChildren().add(this.obsClic.imageTour);
            System.out.println("dedans");
        }
        if (obsClic.isEstSelectionne()){
            //System.out.println(mouseEvent.getX() + ", " + mouseEvent.getY());


            mouseX.set(mouseEvent.getX());
            mouseY.set(mouseEvent.getY());

            if(mouseEvent.getX() < 500){
                //imP.getChildren().get(0).toFront();
                this.obsClic.imageTour.setVisible(false);
            }else {
                this.obsClic.imageTour.setVisible(true);
            }




        }

         */
    }
}

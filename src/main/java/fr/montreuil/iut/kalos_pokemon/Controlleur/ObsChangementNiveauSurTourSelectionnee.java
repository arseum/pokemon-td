package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Permet d'avoir un affichage dynamique du prix de revente et d'amélioration sur le panneau de caractérisque de la tour.
 * Lorsque que le niveau de la tour change les prix se mettent à jour
 */
public class ObsChangementNiveauSurTourSelectionnee implements ChangeListener<Number> {
    private ObsClicSurTour obsClicSurTour;
    private Label niveauTour;
    private Button vendreTourMenu;
    private Button ameliorerTourMenu;

    public ObsChangementNiveauSurTourSelectionnee(ObsClicSurTour obsClicSurTour, Label niveauTour, Button vendreTourMenu, Button ameliorerTourMenu){
        this.obsClicSurTour = obsClicSurTour;
        this.niveauTour = niveauTour;
        this.vendreTourMenu = vendreTourMenu;
        this.ameliorerTourMenu = ameliorerTourMenu;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        Tour t = obsClicSurTour.game.retourneTourAPartirId(obsClicSurTour.idTourSelectionnee.get());
        if(t != null){
            niveauTour.setText("Niveau " + t.getLevel());
            vendreTourMenu.setText("Vendre (" + t.prixRevente() + "$)");
            ameliorerTourMenu.setText("Améliorer (" + t.prixAmelioration() + "$)");
        }
    }
}
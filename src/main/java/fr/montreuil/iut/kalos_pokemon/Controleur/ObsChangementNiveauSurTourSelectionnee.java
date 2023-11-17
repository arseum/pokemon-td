package fr.montreuil.iut.kalos_pokemon.Controleur;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Permet d'avoir un affichage dynamique du prix de revente et d'amélioration sur le panneau de caractérisque de la tour.
 * Lorsque que le niveau de la tour change les prix se mettent à jour
 */
public class ObsChangementNiveauSurTourSelectionnee implements ChangeListener {
    private ObsClicSurTour obsClicSurTour;
    private Label niveauTour;
    private Label compteurDegats;
    private Button vendreTourMenu;
    private Button ameliorerTourMenu;

    public ObsChangementNiveauSurTourSelectionnee(ObsClicSurTour obsClicSurTour, Label niveauTour, Button vendreTourMenu, Button ameliorerTourMenu,Label compteurDegats){
        this.obsClicSurTour = obsClicSurTour;
        this.niveauTour = niveauTour;
        this.vendreTourMenu = vendreTourMenu;
        this.ameliorerTourMenu = ameliorerTourMenu;
        this.compteurDegats = compteurDegats;
    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1){
        Tour t = obsClicSurTour.game.retourneTourAPartirId(obsClicSurTour.idTourSelectionnee.get());
        if(t != null){
            niveauTour.setText("Niveau " + t.getLevel());
            vendreTourMenu.setText("Vendre (" + t.prixRevente() + "$)");
            ameliorerTourMenu.setText("Améliorer (" + t.prixAmelioration() + "$)");
            compteurDegats.setText("Total dégâts : " + (int)t.getCompteurDegats());
        }
    }
}

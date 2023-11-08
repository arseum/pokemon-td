package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;

/**
 * Permet de rendre les images des tours du menu d'achat grisées lorsque l'utilisateur n'a pas assez de pokédollar pour l'achat
 */
public class ObsPokedollarMenuAchat implements ChangeListener<Number> {
    private final HBox conteneur;
    private final String[] listeTours;

    public ObsPokedollarMenuAchat(HBox conteneur, String[] listeTours) {
        this.conteneur = conteneur;
        this.listeTours = listeTours;
    }

    private void changementCouleurTour(String nom, int argentActuel) {
        //conteneur.lookup("#tourMenuSprite_" + nom + "_normal").setVisible(argentActuel >= Parametres.prixTour(nom));
        conteneur.lookup("#tourMenuSprite_" + nom + "_normal").setVisible(argentActuel >= PokemonEnum.valueOf(nom).getPrix());
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        int argentActuel = (int) observableValue.getValue();
        for (String nomTour : this.listeTours) {
            changementCouleurTour(nomTour, argentActuel);
        }
    }
}
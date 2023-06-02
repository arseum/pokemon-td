package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.TourZone;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Magneti extends Tour {

    private final ObservableList<Ennemi> ennemisCible;
    private final BooleanProperty actif;

    public Magneti(int x, int y, Game game) {
        super(70, 0, "neutre", 70, x, y, "magneti", 4, game);
        ennemisCible = FXCollections.observableArrayList();
        actif = new SimpleBooleanProperty(false);
    }

    public ObservableList<Ennemi> getEnnemisCible() {
        return ennemisCible;
    }

    public BooleanProperty actifProperty() {
        return actif;
    }

    /**
     * il faudrait appeler cette methode le + souvent possible (toute les 4/5 frame de dirais)
     */
    @Override
    public void attaque() {

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //reset les vitesses des ennemis qui ont été slow avant et supprime les ennemie mort
        for (int i = ennemisCible.size() - 1; i >= 0; i--) {
            if (ennemisCible.get(i).getHp() <= 0 || distance(ennemisCible.get(i)) > portee) {
                ennemisCible.get(i).resetVitesse();
                ennemisCible.remove(i);
            }
        }

        //cherche les ennemis en portée
        for (Ennemi e : listEnnemi) {
            if (this.distance(e) <= portee)
                ennemisCible.add(e);
        }

        //slow les ennemis en range
        for (Ennemi e : ennemisCible) {
            e.reduitVitese(1);
        }

        actif.setValue(ennemisCible.size() > 0);

    }
}

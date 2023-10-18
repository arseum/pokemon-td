package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.ImmobilisationZone;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Zone;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Magneti extends Tour {

    private final ObservableList<Ennemi> ennemisCible;
    private final BooleanProperty actif;
    private final Zone zone;
    private int valeurSlow;


    public Magneti(int x, int y) {
        super(90, 0, Pokemon.magneti.name(), Pokemon.magneti.getPrix(), x, y, Pokemon.magneti.name(), 0,null);
        ennemisCible = FXCollections.observableArrayList();
        actif = new SimpleBooleanProperty(false);
        zone = new Zone(this, game);
        valeurSlow = 1;
        setMyCompetence(new ImmobilisationZone(this,90));
    }

    public BooleanProperty actifProperty() {
        return actif;
    }

    public boolean isActif() {
        return actif.get();
    }

    public Zone getZone() {
        return zone;
    }

    @Override
    public void setGame(Game game) {
        super.setGame(game);
        game.ajouteProjectile(zone);
    }

    @Override
    public void amelioreStats() {
        portee.set(portee.get() + (6*level.get()));
        if(level.get() == Parametres.niveauEvolutionTour) {
            valeurSlow = 2;
        }
    }



    /**
     * il faudrait appeler cette methode le + souvent possible (toute les 4/5 frame de dirais)
     */
    @Override
    public void attaque() {

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        updateListCibles();

        //cherche les ennemis en portée
        for (Ennemi e : listEnnemi) {
            if (estADistance(e))
                ennemisCible.add(e);
        }

        //slow les ennemis en range
        for (Ennemi e : ennemisCible) {
            e.reduitVitese(valeurSlow);
        }

        actif.setValue(ennemisCible.size() > 0);

    }

    /**
     * reset les vitesses des ennemis qui ont été slow avant et supprime les ennemie mort
     */
    private void updateListCibles() {
        for (int i = ennemisCible.size() - 1; i >= 0; i--) {
            if (ennemisCible.get(i).getHp() <= 0 || Parametres.distance(this,ennemisCible.get(i)) > portee.get()) {
                ennemisCible.get(i).resetVitesse();
                ennemisCible.remove(i);
            }
        }
    }

    /**
     * permet de retirer la son animation dans la vue et
     * de retirer les slow sur les ennemi
     */
    public void vendre(){
        game.remove(zone);

        for (Ennemi e : ennemisCible)
            e.resetVitesse();
    }
}

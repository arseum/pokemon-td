package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Nidoran extends TourPoisson {

    public Nidoran(int x, int y) {
        super(115, 3, "neutre", Parametres.prixnidoran, x, y, "nidoran", 30,2);
    }


    @Override
    protected void amelioreStats() {
        degatsPoison += 2;
        this.degats += 4;
    }

    @Override
    public void evolution() {
        super.evolution();
        portee.set(portee.get()+10);
    }

    @Override
    protected boolean peutCibler(Ennemi ennemi) {
        return !ennemiEmpoisone.contains(ennemi) && estADistance(ennemi);
    }

}

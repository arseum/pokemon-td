package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Nidoran extends TourActif {

    private ObservableList<Ennemi> ennemiEmpoisone;
    private int degatsPoison;

    public Nidoran(int x, int y) {
        super(115, 3, "neutre", Parametres.prixnidoran, x, y, "nidoran", 30);
        //ennemiEmpoisone = new ArrayList<>();
        ennemiEmpoisone = FXCollections.observableArrayList();
        degatsPoison = 2;
    }

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }

    @Override
    protected void amelioreStats() {
        degatsPoison += 2;
        this.degats += 4;
        if (level.get() == Parametres.niveauEvolutionTour)
            portee.set(portee.get()+10);
    }

    @Override
    public void actif() {

        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).reduitVitesseMax(1);
        }

        tempProchainActif.set(game.getNbFrameValue() + (60*12));

    }

    @Override
    protected boolean peutCibler(Ennemi ennemi) {
        return !ennemiEmpoisone.contains(ennemi) && estADistance(ennemi);
    }

    public void ajouteEnnemiEmpoissoner(Ennemi e){
        ennemiEmpoisone.add(e);
    }

    public void apliquePoison() {
        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            if (ennemiEmpoisone.get(i).getHp() > 0) //si la cible est mort entre temps
                ennemiEmpoisone.get(i).diminueHP(degatsPoison);
                //le poisson etant neutre pas besoin de faire un calcul avec les types
            else
                ennemiEmpoisone.remove(i);
        }
    }
}

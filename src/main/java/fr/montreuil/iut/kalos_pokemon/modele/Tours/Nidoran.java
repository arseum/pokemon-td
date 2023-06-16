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
        super(128, 4, "neutre", Parametres.prixnidoran, x, y, "nidoran", 30);
        //ennemiEmpoisone = new ArrayList<>();
        ennemiEmpoisone = FXCollections.observableArrayList();
        degatsPoison = 2;
    }

    @Override
    protected void amelioreStats() {
        degatsPoison += 2;
        this.degats += 4;
        if (level.get() == Parametres.niveauEvolutionTour) {
            portee.set(portee.get() +10);
        }
    }

    @Override
    public void actif() {

        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).reduitVitesseMax(1);
        }

        tempProchainActif.set(game.getNbFrameValue() + (60*5));

    }

    @Override
    public void attaque() {

        Ennemi cible = null;
        int index = 0;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //chercher si on peut empoisoner un nouvel ennemi
        while (cible == null && index < listEnnemi.size()) {

            if (!ennemiEmpoisone.contains(listEnnemi.get(index)) && Parametres.distance(this,listEnnemi.get(index)) <= portee.get())
                cible = listEnnemi.get(index);
            else
                index++;

        }

        if (cible != null) {
            game.ajouteProjectile(new Projectile(this, cible, game));
            tempProchaineAttaque = game.getNbFrameValue() + attaqueSpeed;
        }

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

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }
}

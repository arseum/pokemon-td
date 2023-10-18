package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.Poison;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.SlowEnnemiEmpoissone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TourPoison extends TourAvecType implements TourSpe {

    protected ObservableList<Ennemi> ennemiEmpoisone;
    protected int degatsPoison;
    private Seconde dureePoison;
    public TourPoison(Tour t, int degatsPoison, Seconde dureePoison) {
        super(t);
        ennemiEmpoisone = FXCollections.observableArrayList();
        this.degatsPoison = degatsPoison;
        this.dureePoison = dureePoison;
        setMyCompetence(new SlowEnnemiEmpoissone(this));
    }

    @Override
    protected boolean peutCibler(Ennemi ennemi) {
        return !ennemiEmpoisone.contains(ennemi) && estADistance(ennemi);
    }

    public void ajouteEnnemiEmpoissoner(Ennemi e){
        ennemiEmpoisone.add(e);
    }

    public void supprimerEnnemiEmpoissoner(Ennemi e) {ennemiEmpoisone.remove(e);}

    public ObservableList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }

    @Override
    public void amelioreStats() {
        myTour.amelioreStats();
        degatsPoison += 2; //todo a verfier ct combien jspu
    }

    @Override
    public void lanceProjectile(Ennemi cible, ArrayList<EffetImpact> listEffect) {
        listEffect.add(new Poison(degatsPoison,dureePoison,new Seconde(0.2), this));
        ajouteEnnemiEmpoissoner(cible);
        //todo il faut que je retire la memorisation des ennemi stocker pour
        //faire en sorte que une tour poison recupere un ennemi qui n'a pas d'effet poison
        //puis qui recupere l'ennemi empoisoner le + proche si pas le choix

        super.lanceProjectile(cible,listEffect);
    }

}

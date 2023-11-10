package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public class ExplosionAutourTour extends ClassicCompetence{

    private BooleanProperty actif;
    public ExplosionAutourTour(Tour myTour) {
        super(myTour, new Seconde(22));
        actif = new SimpleBooleanProperty(false);
    }

    public BooleanProperty actifProperty() {
        return actif;
    }

    public void activation(){
        actif.set(!actif.get());
    }

    @Override
    public void actif() {

        //List<Ennemi> listEnnemi = myTour.getGame().getListEnnemi().stream().toList();
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();
        Ennemi e;
        double damage;

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            e = listEnnemi.get(i);
            if (myTour.estADistance(e) ) {
                //damage = Parametres.calculDegats(myTour.getType(),e.getType(),150);
                damage = myTour.getType().calculDegats(e.getType(),150);
                e.diminueHP(damage);
                myTour.ajouteDegats(damage);
            }
        }
        //tempProchainActif.set(myTour.getGame().getNbFrameValue() + cooldown.getTempFrameInt()) ;
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt()) ;

        //permet de provoquer une animation dans la vue
        activation();

    }
}

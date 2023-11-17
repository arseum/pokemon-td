package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.TypeEffet;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;

public class SlowEnnemiEmpoissone extends ClassicCompetence{
    public SlowEnnemiEmpoissone(Tour myTour) {
        super(myTour, new Seconde(12));
    }

    @Override
    public void actif() {
        Ennemi e;
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi();

        //TODO MAEJEUR
        // a refactoring + repenser si c bien
        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            e = listEnnemi.get(i);
            if (e.estAffecterParEffet(TypeEffet.Poison)){
                EffetImpact effetImpact = e.getEffectSelonType(TypeEffet.Poison);
                if (effetImpact.getTireur() == myTour){
                    e.reduitVitese(1);
                }
            }
        }

        //tempProchainActif.set(myTour.getGame().getNbFrameValue() + cooldown.getTempFrameInt());
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt());

    }


}

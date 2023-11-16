package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.TypeEffet;
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

        //TODO cette actif est un peu bizarre...
        // pourquoi pas faire en sorte qu'il reset le poison + applique un
        // ralentissement durant la nouvelle perdiode de poison

        Ennemi e;
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi();

        //todo a refactoring + repenser si c bien
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

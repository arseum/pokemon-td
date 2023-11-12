package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ModeTirEnContinue extends ModeTireur {
    public ModeTirEnContinue(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }


    //TODO doit cibler le meme a changer
    @Override
    public void attaque() {
        Ennemi e = chercheCible();
        if(e != null){
            e.ajouteEffet(this.effetAttaque);
        }
    }
}

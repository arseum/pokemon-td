package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

//TODO MAJEUR
// Implementer effet ralentissement
// ceci est l'effet coresondant a un rallentissement de zone
public class EffetRalentissementDeZone extends EffetImpact{
    private int xTour;
    private int yTour;
    private int porteeTour;
    private int reductionVitesse;
    private boolean aEteApplique;
    public EffetRalentissementDeZone(Tour t, int reductionVitesse) {
        super(t);
        this.xTour = t.getX();
        this.yTour = t.getY();
        this.porteeTour = t.getPortee();
        this.reductionVitesse = reductionVitesse;
        this.aEteApplique = false;
    }

    @Override
    public void initEffetType() {
        this.typeEffet = TypeEffet.RalentissementDeZone;
    }

    @Override
    public boolean peutEtreApplique(Ennemi ennemi) {
        return ennemi.getVitesseMax()==ennemi.getVitesseActuel();
    }

    @Override
    public void appliqueEffet(Ennemi ennemi) {
        //System.out.print(ennemi.getVitesseActuel());
        ennemi.reduitVitese(this.reductionVitesse);
        //System.out.println(", " + ennemi.getVitesseActuel());
    }
    @Override
    public boolean finEffet(Ennemi ennemi) {
        if(!Game.getGame().getListEnnemi().contains(tireur) || !tireur.estADistance(ennemi)){
            //System.out.println(ennemi + " " + "resetVitesse");
            ennemi.resetVitesse();
            return true;
        }
        return false;
    }
}

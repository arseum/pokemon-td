package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

//TODO Ne marche pas quand ennemi va trop vite (logique car condition est sorti de "zone")
// Revoir la condition (Après tout ça reste un projectile)
// FIXME une maniere serait de dire que un projectile a une vitesse
public class EffetRalentissement extends EffetImpact{
    private int xTour;
    private int yTour;
    private int porteeTour;
    private int reductionVitesse;
    private boolean aEteApplique;
    public EffetRalentissement(Tour t, int reductionVitesse) {
        super(t);
        this.xTour = t.getX();
        this.yTour = t.getY();
        this.porteeTour = t.getPortee();
        this.reductionVitesse = reductionVitesse;
        this.aEteApplique = false;
    }

    @Override
    public void initEffetType() {
        this.typeEffet = TypeEffet.Ralentissement;
    }

    @Override
    public boolean peutEtreApplique(Ennemi ennemi) {
        /*
        if(this.aEteApplique)
            return false;
        else{
            this.aEteApplique = true;
            return true;
        }

         */
        return ennemi.getVitesseMax()==ennemi.getVitesseActuel();
    }

    @Override
    public void appliqueEffet(Ennemi ennemi) {
        ennemi.reduitVitese(this.reductionVitesse);
    }

    @Override
    public boolean finDeVie(Ennemi ennemi) {
        /*
        //Cas de vente
        //FIXME à tester
        if(tireur == null)
            return true;
        else
            return !tireur.estADistance(victime);

         */
        if(!tireur.estADistance(ennemi)){
            System.out.println(ennemi + " " + "resetVitesse");
            ennemi.resetVitesse();
            return true;
        }
        return false;
    }
}

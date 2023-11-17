package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class EffetRalentissement extends EffetImpact{

    private boolean aEteApplique;

    //Doit être entre 0 et 100
    private int tauxDeReductionVitesse;
    private Seconde dureeDeRalentissement;
    public EffetRalentissement(Tour t, int tauxDeReductionVitesse, Seconde dureeDeRalentissement) {
        super(t);
        this.aEteApplique = false;
        this.tauxDeReductionVitesse = tauxDeReductionVitesse;
        this.dureeDeRalentissement = dureeDeRalentissement;
    }

    @Override
    public void initEffetType() {
        //TODO temp
        this.typeEffet = TypeEffet.Ralentissement;
    }

    @Override
    public boolean peutEtreApplique(Ennemi ennemi) {
        if(this.aEteApplique == false){
            return true;
        }
        return false;
    }

    @Override
    public void appliqueEffet(Ennemi ennemi) {
        int vitesseReduite = ennemi.getVitesseMax() * (100 - tauxDeReductionVitesse)/100;
        ennemi.setVitesseActuel(vitesseReduite);
        this.aEteApplique = true;
    }

    @Override
    public boolean finEffet(Ennemi ennemi) {
        boolean finEffet = ennemi != null && Game.getGame().getNbFrameValue() > frameDebutDeVie + dureeDeRalentissement.getTempFrameDouble();
        if(finEffet){
            ennemi.setVitesseActuel(ennemi.getVitesseMax());
        }
        return finEffet;
    }
}

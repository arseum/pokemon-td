package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgePoisson implements ForgeEffectImpact{

    private int degatPoison;
    private Seconde dureePoison;
    private Seconde frequencePoison;
    private final Tour tireur;

    public ForgePoisson(int degatPoison, Seconde dureePoison, Seconde frequencePoison, Tour tireur) {
        this.degatPoison = degatPoison;
        this.dureePoison = dureePoison;
        this.frequencePoison = frequencePoison;
        this.tireur = tireur;
    }

    /** GETTER + SETTER */
    public void setDegatPoison(int degatPoison) {
        this.degatPoison = degatPoison;
    }

    public void setDureePoison(Seconde dureePoison) {
        this.dureePoison = dureePoison;
    }

    public void setFrequencePoison(Seconde frequencePoison) {
        this.frequencePoison = frequencePoison;
    }

    /** methode public */
    @Override
    public EffetImpact genereEffect() {
        return new EffetPoison(degatPoison,dureePoison,frequencePoison,tireur);
    }


}
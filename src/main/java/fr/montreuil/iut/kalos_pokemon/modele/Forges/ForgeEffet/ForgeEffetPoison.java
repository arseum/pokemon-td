package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetPoison;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeEffetPoison implements ForgeEffetImpact {
    private int degatPoison;
    private Seconde dureePoison;
    private Seconde frequencePoison;
    private final Tour tireur;

    public ForgeEffetPoison(Tour tireur,int degatPoison, Seconde dureePoison, Seconde frequencePoison ) {
        this.degatPoison = degatPoison;
        this.dureePoison = dureePoison;
        this.frequencePoison = frequencePoison;
        this.tireur = tireur;
    }

    @Override
    public EffetImpact genereEffect() {
        return new EffetPoison(degatPoison, dureePoison, frequencePoison, tireur);
    }


}

package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class AttaqueInstantanee extends EntiteAttaque {
    public AttaqueInstantanee(Tour tour, ForgeEffetImpact effetImpacts, double degats, Ennemi cible) {
        super(tour, effetImpacts, degats, cible);
    }

    @Override
    public void gestionEntiteAttaque() {
        appliqueAttaque(this.cible);
    }

}

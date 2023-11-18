package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.EntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe a pour responsabilité de definir la manière dont la tour va cibler.
 */
public abstract class ModeDeCiblage {
    /**
     * un mode d'attaque est forcement lié a une tour (compteur degats...)
     */
    protected Tour tour;

    public ModeDeCiblage(Tour tour) {
        this.tour = tour;
    }

    public abstract void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteAttaque forgeEntiteAttaque);

    protected Ennemi chercheCible() {
        return chercheCibles().isEmpty() ? null : chercheCibles().get(0);
    }

    protected List<Ennemi> chercheCibles() {
        List<Ennemi> ennemisAPortee = new ArrayList<>();
        Game.getGame().getListEnnemi().forEach(e -> {
            if (tour.estADistance(e))
                ennemisAPortee.add(e);
        });
        return ennemisAPortee;

    }

    protected void lanceEntiteAttaque(ForgeEntiteAttaque forgeEntiteAttaque, ForgeEffetImpact forgeEffet, int degat, Ennemi e) {
        EntiteAttaque a = forgeEntiteAttaque.genereAttaque(tour, forgeEffet, degat, e);
        a.gestionEntiteAttaque();
    }
}

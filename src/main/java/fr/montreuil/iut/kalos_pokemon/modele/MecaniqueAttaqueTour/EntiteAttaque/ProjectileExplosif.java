package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;

public class ProjectileExplosif extends Projectile {
    private final int rayonExploxion;

    public ProjectileExplosif(Tour tour, Ennemi ennemi, ForgeEffetImpact forgeEffetImpact, double degats, int rayonExploxion) {
        super(tour, ennemi, forgeEffetImpact, degats);
        this.rayonExploxion = rayonExploxion;
    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }

    @Override
    public void gestionTir() {
        List<Ennemi> listeEnnemi = Game.getGame().getListEnnemi().stream().toList();

        for (int i = listeEnnemi.size() - 1; i >= 0; i--) {
            Ennemi ennemi = listeEnnemi.get(i);
            if (Parametres.distance(this.getX(), this.getY(), ennemi.getX(), ennemi.getY()) <= this.rayonExploxion) {
                appliqueAttaque(listeEnnemi.get(i));
            }
        }

        finTir();
    }
}

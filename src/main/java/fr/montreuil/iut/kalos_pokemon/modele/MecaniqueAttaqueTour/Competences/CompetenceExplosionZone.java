package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeProjectileExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class CompetenceExplosionZone extends NewCompetence{
    public CompetenceExplosionZone(Tour myTour, Seconde cooldown) {
        super(
                myTour,
                cooldown,
                new ModeCiblageZone(myTour),
                0,
                new ForgeProjectileExplosif(50),
                new ForgeEffetNull(myTour));
    }
}

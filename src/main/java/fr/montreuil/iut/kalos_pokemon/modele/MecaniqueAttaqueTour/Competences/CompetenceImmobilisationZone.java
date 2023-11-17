package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class CompetenceImmobilisationZone extends NewCompetence{
    public CompetenceImmobilisationZone(Tour myTour, Seconde cooldown, Seconde dureeImmobilisation) {
        super(
                myTour,
                cooldown,
                new ModeCiblageZone(myTour),
                0,
                new ForgeEntiteAttaqueInstantane(),
                new ForgeRalentissement(myTour, 100, dureeImmobilisation)
        );
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeRalentissement;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageEnnemisEmpoisonnes;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class CompetenceRalentissementEnnemiPoison extends NewCompetence {
    public CompetenceRalentissementEnnemiPoison(Tour myTour, Seconde cooldown) {
        super(
                myTour,
                cooldown,
                new ModeCiblageEnnemisEmpoisonnes(myTour),
                0,
                new ForgeEntiteAttaqueInstantane(),
                new ForgeRalentissement(myTour,
                        50,
                        new Seconde(3)));
    }
}

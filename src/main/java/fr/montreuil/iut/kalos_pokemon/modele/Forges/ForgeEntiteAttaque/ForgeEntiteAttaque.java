package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.EntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public interface ForgeEntiteAttaque {
    EntiteAttaque genereAttaque(Tour t, ForgeEffetImpact forgeEffet, int degats, Ennemi e);
}

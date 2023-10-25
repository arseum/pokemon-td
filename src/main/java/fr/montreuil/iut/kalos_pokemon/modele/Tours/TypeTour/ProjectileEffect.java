package fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;

import java.util.ArrayList;

public interface ProjectileEffect {

    void lanceProjectile(Ennemi cible, ArrayList<EffetImpact> listEffect);
    void ameliorationEffect();

}

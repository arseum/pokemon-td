package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Attaque;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.AttaqueDirect;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeAttaqueDirect implements ForgeAttaque{

    @Override
    public Attaque genereAttaque(Tour t, ForgeEffectImpact forgeEffet,
                                 int degats, Ennemi e) {
        return new AttaqueDirect(t,forgeEffet,degats,e);
    }
}

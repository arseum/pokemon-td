package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Attaque;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeProjectile implements ForgeAttaque{

    public Attaque genereAttaque(Tour t, ForgeEffectImpact forgeEffet, int degats, Ennemi e){
        return new Projectile(t,e,forgeEffet,degats);
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteDeDommage.EntiteDeDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteDeDommage.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeProjectile implements ForgeEntiteDommage {

    public EntiteDeDommage genereAttaque(Tour t, ForgeEffetImpact forgeEffet, int degats, Ennemi e){
        return new Projectile(t,e,forgeEffet,degats);
    }
}

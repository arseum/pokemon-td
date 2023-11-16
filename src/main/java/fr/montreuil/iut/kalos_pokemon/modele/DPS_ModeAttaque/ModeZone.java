package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
public class ModeZone extends ModeAttaque {
    public ModeZone(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffectImpact forgeEffet, ForgeAttaque forgeAttaque) {
        chercheCibles().forEach(ennemie -> {
            if (tourCible.estADistance(ennemie))
                lanceProjectile(forgeAttaque,forgeEffet,degats,ennemie);
        });
    }
}

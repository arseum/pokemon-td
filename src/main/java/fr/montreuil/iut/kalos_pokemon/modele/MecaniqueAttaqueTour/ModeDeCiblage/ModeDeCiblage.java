package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.EntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

//TODO À changer (trop de repetition de for each)
/**
 * Une tour ne peut avoir qu'un mode attaque. Ce mode peut être amené à changer
 * Cette classe a pour responsabilité de definir la manière dont la tour va cibler.
 * Elle va donc instancier un projectile qui contient l'effet du mode attaque
 * (C'est la classe projectile qui a pour responsabilité d'appliquer les éventuels dégats et effets)
 */
public abstract class ModeDeCiblage {
    /**
     * un mode d'attaque est forcement lié a une tour (compteur degats...)
     */
    protected Tour tourCible;

    public ModeDeCiblage(Tour tourCible) {
        this.tourCible = tourCible;
    }

    public abstract void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteDommage forgeEntiteDommage);

    protected Ennemi chercheCible() {
        return chercheCibles().isEmpty()? null : chercheCibles().get(0);
    }

    protected List<Ennemi> chercheCibles(){
        List<Ennemi> ennemisAPortee = new ArrayList<>();
        Game.getGame().getListEnnemi().forEach(e -> {
            if (tourCible.estADistance(e))
                ennemisAPortee.add(e);
        });
        return ennemisAPortee;

    }
    protected void lanceEntiteAttaque(ForgeEntiteDommage forgeEntiteDommage, ForgeEffetImpact forgeEffet, int degat, Ennemi e){
        EntiteAttaque a = forgeEntiteDommage.genereAttaque(tourCible,forgeEffet,degat,e);
        a.gestionEntiteAttaque();
    }
}

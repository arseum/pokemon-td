package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteDeDommage.EntiteDeDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

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

    public abstract void attaque(int degats, ForgeEffectImpact forgeEffet, ForgeAttaque forgeAttaque);

    protected Ennemi chercheCible() {
        return chercheCibles().isEmpty()? null : chercheCibles().get(0);
//        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();
//
//        for (Ennemi ennemi : listEnnemi) {
//            if (this.tourCible.estADistance(ennemi))
//                return ennemi;
//        }
//        return null;
    }

    protected List<Ennemi> chercheCibles(){
        List<Ennemi> ennemisAPortee = new ArrayList<>();
        Game.getGame().getListEnnemi().forEach(e -> {
            if (tourCible.estADistance(e))
                ennemisAPortee.add(e);
        });
        return ennemisAPortee;

    }

    //todo z renommer
    protected void lanceProjectile(ForgeAttaque forgeAttaque,ForgeEffectImpact forgeEffet, int degat,Ennemi e){
        //Game.getGame().ajouteProjectile(forgeAttaque.genereAttaque(tourCible,forgeEffet,degat,e));
        EntiteDeDommage a = forgeAttaque.genereAttaque(tourCible,forgeEffet,degat,e);
        a.traitementEntiteDommage();
    }
}
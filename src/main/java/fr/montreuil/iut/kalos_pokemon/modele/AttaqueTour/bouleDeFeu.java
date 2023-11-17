package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

public class bouleDeFeu extends Projectile {

    private final int rayonExploxion;

    public bouleDeFeu(Tour tour, Ennemi ennemi, ForgeEffectImpact forgeEffectImpact, double degats, int rayonExploxion) {
        super(tour,ennemi,forgeEffectImpact,degats);
        this.rayonExploxion = rayonExploxion;
    }

    @Override
    protected void explotionTir(double degatsReel) {
        List<Ennemi> list = Game.getGame().getListEnnemi().stream().toList();

        for (int i = list.size() - 1; i >= 0 ; i--)
            affecteSiPossible(list.get(i),degatsReel);

    }

    public int getRayonExploxion() {
        return rayonExploxion;
    }
    @Override
    protected boolean estToucherParExplotion(Ennemi e){
        //return Parametres.distance((Objet) this,e) <= rayonExploxion && e.getHp() > 0;
        return distanceIci() <= rayonExploxion && e.getHp() > 0;
    }
}

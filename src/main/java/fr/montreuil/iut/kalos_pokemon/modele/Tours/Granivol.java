package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Granivol extends Tour {

    public Granivol(int x, int y) {
        super(160, 3, "plante", Parametres.prixgranivol, x, y, "granivol", 6);
    }

    @Override
    public void levelUp() {
        /*
        this.level.set(level.get()+1);

        this.portee.set(180);

         */
        int niveauActuel = this.level.get();
        if((Parametres.niveauEvolutionTour - niveauActuel) > 1){
            this.portee.set(this.portee.get() + 5);
            this.level.set(niveauActuel + 1);
        } else if ((Parametres.niveauEvolutionTour - niveauActuel) == 1) {
            this.portee.set(this.portee.get() + 10);
            this.setNom(Parametres.nomEvolutionGranivol);
            this.level.set(niveauActuel + 1);
        }
    }

    @Override
    public void actif() {

    }
}

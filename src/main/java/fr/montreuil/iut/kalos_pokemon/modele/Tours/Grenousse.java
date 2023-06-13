package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Grenousse extends Tour {
    public Grenousse(int x, int y) {
        super(160, 40, "eau", Parametres.prixgrenousse, x, y, "grenousse", 90);
    }

    @Override
    public void levelUp() {
        /*
        this.level.set(level.get()+1);

        this.degats = 80;
        this.attaqueSpeed = 75;
         */
        int niveauActuel = this.level.get();
        if((Parametres.niveauEvolutionTour - niveauActuel) > 1){
            this.degats += 10;
            this.attaqueSpeed -= 3;
            this.level.set(niveauActuel + 1);
        } else if ((Parametres.niveauEvolutionTour - niveauActuel) == 1) {
            this.degats += 15;
            this.attaqueSpeed -= 5;
            this.setNom(Parametres.nomEvolutionGrenousse);
            this.level.set(niveauActuel + 1);
        }
    }

    @Override
    public void actif() {

    }
}

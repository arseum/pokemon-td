package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class Poussifeu extends Tour {
    public Poussifeu(int x, int y) {
        super(96, 20, "feu", Parametres.prixpoussifeu, x, y, "poussifeu", 60);
    }


    @Override
    public void levelUp() {
        /*
        super.levelUp();

        this.degats *= 1.5;
        this.attaqueSpeed -= 5;
        */
        int niveauActuel = this.level.get();
        if((Parametres.niveauEvolutionTour - niveauActuel) > 1){
            this.degats += 5;
            this.attaqueSpeed -= 2;
            this.level.set(niveauActuel + 1);
        } else if ((Parametres.niveauEvolutionTour - niveauActuel) == 1) {
            this.degats += 7;
            this.attaqueSpeed -= 4;
            this.setNom(Parametres.nomEvolutionPoussifeu);
            this.level.set(niveauActuel + 1);
        }
    }

    @Override
    public void actif() {

    }
}

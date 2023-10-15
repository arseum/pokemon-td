package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.SlowEnnemiEmpoissone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour.TourPoison;

public class Nidoran extends TourPoison {



    public Nidoran(int x, int y) {
        super(115, 3, "neutre", Parametres.prixnidoran, x, y, "nidoran", 30, null,2);
        setMyCompetence(new SlowEnnemiEmpoissone(this));
    }

    @Override
    protected void amelioreStats() {
        degatsPoison += 2;
        this.degats += 4;
        if (level.get() == Parametres.niveauEvolutionTour)
            portee.set(portee.get()+10);
    }


}

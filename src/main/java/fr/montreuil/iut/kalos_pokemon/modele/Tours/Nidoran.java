package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

import java.util.ArrayList;
import java.util.List;

public class Nidoran extends Tour {

    private final ArrayList<Ennemi> ennemiEmpoisone;
    private int degatsPoison;

    public Nidoran(int x, int y) {
        super(128, 4, "neutre", Parametres.prixnidoran, x, y, "nidoran", 14, 20);
        ennemiEmpoisone = new ArrayList<>();
        degatsPoison = 2;
    }

    @Override
    public void levelUp() {
        /*
        this.level.set(level.get()+1);

        degatsPoison = 5;
        this.degats = 10;

         */
        int niveauActuel = this.level.get();
        if((Parametres.niveauEvolutionTour - niveauActuel) > 1){
            this.degats += 2;
            this.level.set(niveauActuel + 1);
        } else if ((Parametres.niveauEvolutionTour - niveauActuel) == 1) {
            this.degats += 4;
            this.degatsPoison = 5;
            this.setNom(Parametres.nomEvolutionNidoran);
            this.level.set(niveauActuel + 1);
        }
    }

    @Override
    public void actif() {

    }

    @Override
    public void attaque() {

        Ennemi cible = null;
        int index = 0;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //chercher si on peut empoisoner un nouvel ennemi
        while (cible == null && index < listEnnemi.size()) {

            if (!ennemiEmpoisone.contains(listEnnemi.get(index)) && distance(listEnnemi.get(index)) <= portee.get())
                cible = listEnnemi.get(index);
            else
                index++;

        }

        if (cible != null) {
            ennemiEmpoisone.add(cible);
            game.ajouteProjectile(new Projectile(this, cible, game));
            tempProchaineAttaque = game.getNbFrame() + attaqueSpeed;
        }

    }

    public void apliquePoison() {
        //faire des degats au ennemi emposoné
        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).diminueHP(degatsPoison, Parametres.typeNeutre);
            if (ennemiEmpoisone.get(i).getHp() <= 0)
                ennemiEmpoisone.remove(i);
        }
    }

}

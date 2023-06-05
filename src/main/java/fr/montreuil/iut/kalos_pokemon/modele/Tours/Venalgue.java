package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

import java.util.ArrayList;
import java.util.List;

public class Venalgue extends Tour {

    private ArrayList<Ennemi> ennemiEmpoisone;
    private int degatsPoison;

    public Venalgue(int x, int y) {
        super(128, 4, "neutre", Parametres.prixvenalgue, x, y, "venalgue", 14,20);
        ennemiEmpoisone = new ArrayList<>();
        degatsPoison = 2;
    }

    @Override
    public void attaque() {

        Ennemi cible = null;
        int index = 0;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //chercher si on peut empoisoner un nouvel ennemi
        while (cible == null && index < listEnnemi.size()) {

            if (!ennemiEmpoisone.contains(listEnnemi.get(index)) && distance(listEnnemi.get(index)) <= portee)
                cible = listEnnemi.get(index);
            else
                index++;

        }

        if (cible != null) {
            game.ajouteProjectile(new Projectile(this,cible,game));
            tempProchaineAttaque = game.getNbFrame() + attaqueSpeed;
        }

    }

    public void apliquePoison(){
        //faire des degats au ennemi emposoné
        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).diminueHP(degatsPoison);
            if (ennemiEmpoisone.get(i).getHp() <= 0)
                ennemiEmpoisone.remove(i);
        }
    }

    public ArrayList<Ennemi> getEnnemiEmpoisone() {
        return ennemiEmpoisone;
    }
}

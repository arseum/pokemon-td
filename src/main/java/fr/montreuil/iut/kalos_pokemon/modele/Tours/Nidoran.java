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
        super(128, 4, "neutre", Parametres.prixnidoran, x, y, "nidoran", 20);
        ennemiEmpoisone = new ArrayList<>();
        degatsPoison = 2;
    }

    @Override
    public void levelUp() {
        this.level.set(level.get()+1);

        degatsPoison = 5;
        this.degats = 10;
    }

    @Override
    public void actif() {

        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).reduitVitese(1);
        }

        //todo les ennemis slow ne doivent pas reset leur vitesse grace au magneti

        tempProchainActif = game.getNbFrame() + (60*20) ;

    }

    @Override
    public void attaque() {

        Ennemi cible = null;
        int index = 0;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //chercher si on peut empoisoner un nouvel ennemi
        while (cible == null && index < listEnnemi.size()) {

            if (!ennemiEmpoisone.contains(listEnnemi.get(index)) && Parametres.distance(this,listEnnemi.get(index)) <= portee.get())
                cible = listEnnemi.get(index);
            else
                index++;

        }

        if (cible != null) {
            game.ajouteProjectile(new Projectile(this, cible, game));
            tempProchaineAttaque = game.getNbFrame() + attaqueSpeed;
        }

    }

    public void ajouteEnnemiEmpoissoner(Ennemi e){
        ennemiEmpoisone.add(e);
    }

    public void apliquePoison() {
        //faire des degats au ennemi emposoné
        for (int i = ennemiEmpoisone.size() - 1; i >= 0; i--) {
            ennemiEmpoisone.get(i).diminueHP(degatsPoison);
            if (ennemiEmpoisone.get(i).getHp() <= 0)
                ennemiEmpoisone.remove(i);
        }
    }

}

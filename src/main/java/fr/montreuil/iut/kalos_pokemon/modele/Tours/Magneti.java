package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

import java.util.ArrayList;
import java.util.List;

public class Magneti extends Tour {

    private ArrayList<Ennemi> ennemisCible;

    public Magneti(int x, int y, Game game) {
        super(64, 0, "neutre", 70, x, y, "magneti", 4, game);
        ennemisCible = new ArrayList<>();
    }

    @Override
    public void attaque() {

        ennemisCible.clear();
        int index = 0;
        int super_x;
        int super_y;
        int distance;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //cherche les ennemis en portée
        for (Ennemi e : listEnnemi){

            super_x = Math.abs(getX() - e.getX());
            super_y = Math.abs(getY() - e.getY());
            distance = (int) Math.sqrt((super_x * super_x) + (super_y * super_y));

            if (distance <= portee)
                ennemisCible.add(e);

        }

        //slow les ennemis en range
        if (ennemisCible.size() != 0) {
            for (Ennemi e : ennemisCible){
                //todo il faut rajouter la mechanique de slow
            }
        }

    }
}

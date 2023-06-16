package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Nidoran;
import javafx.scene.input.MouseEvent;

public class Projectile extends Attaque {

    private final Ennemi cible;
    protected double degatFinal;

    public Projectile(Tour tour, Ennemi ennemi, Game game) {
        super(tour, game);
        cible = ennemi;
        degatFinal = Parametres.calculDegats(tour.getType(),ennemi.getType(),tour.getDegats());
    }

    @Override
    public void bouge() {

        if (doitBouger()) {
            y.set(getY() < cible.getY() ? getY() + 4 : getY() - 4);
            x.set(getX() < cible.getX() ? getX() + 4 : getX() - 4);

            bouge.set(true);
            bouge.set(false);
        } else
            explotionTir();

    }

    protected void explotionTir(){
        if (cible.getHp() > 0) {
            cible.diminueHP(degatFinal);
            if (tireur instanceof Nidoran nidoran)
                nidoran.ajouteEnnemiEmpoissoner(cible);
        }
        game.remove(this);
    }

    private boolean doitBouger(){
        return (getX() > cible.getX() + 15 || getY() > cible.getY() + 15 || getX() < cible.getX() - 15 || getY() < cible.getY() - 15);
    }

}

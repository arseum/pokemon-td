package fr.montreuil.iut.kalos_pokemon.modele;

public class Projectile extends Attaque {

    private final Ennemi cible;

    public Projectile(Tour tour, Ennemi ennemi, Game game) {
        super(tour, game);
        cible = ennemi;
    }

    @Override
    public void bouge() {

        if (doitBouger()) {
            y.set(getY() < cible.getY() ? getY() + 4 : getY() - 4);
            x.set(getX() < cible.getX() ? getX() + 4 : getX() - 4);

            bouge.set(true);
            bouge.set(false);
        } else {
            if (cible.getHp() > 0) {
                cible.diminueHP(tireur.getDegats());
            }
            game.remove(this);
        }

    }

    private boolean doitBouger(){
        return (getX() > cible.getX() + 15 || getY() > cible.getY() + 15 || getX() < cible.getX() - 15 || getY() < cible.getY() - 15);
    }

}

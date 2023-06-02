package fr.montreuil.iut.kalos_pokemon.modele;

public class Projectile extends Attaque {

    private final Ennemi cible;

    public Projectile(Tour tour, Ennemi ennemi, Game game) {
        super(tour, game);
        cible = ennemi;
    }

    @Override
    public void bouge() {

        if (getX() > cible.getX() + 15 || getY() > cible.getY() + 15 || getX() < cible.getX() - 15 || getY() < cible.getY() - 15) {

            for (int i = 0; i < 6; i++) {
                y.set(getY() < cible.getY() ? getY() + 1 : getY() - 1);
                x.set(getX() < cible.getX() ? getX() + 1 : getX() - 1);

                if (idImage.get() + 1 > nbImageMax)
                    idImage.set(0);
                else
                    idImage.set(idImage.get() + 1);

            }


        } else {
            if (cible.getHp() > 0) {
                cible.diminueHP(tireur.getDPS());
                if (cible.getHp() <= 0) {
                    game.remove(cible);
                    game.ajoutePokedollar(cible.getRecompense());
                }
            }
            game.remove(this);
        }

    }
}

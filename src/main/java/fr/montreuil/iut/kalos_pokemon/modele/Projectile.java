package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Nidoran;

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
        } else
            explotionTir();

    }

    protected void explotionTir(){
        if (cible.getHp() > 0) {
            //todo: Zen - temporaire pour merge - avoir le type de tour et passer en param
            cible.diminueHP(tireur.getDegats(), Parametres.typeNeutre);
            if (tireur instanceof Nidoran) {
                ((Nidoran) tireur).ajouteEnnemiEmpoissoner(cible);
                cible.setEstEmpoisonner(true);
            }
        }
        game.remove(this);
    }

    private boolean doitBouger(){
        return (getX() > cible.getX() + 15 || getY() > cible.getY() + 15 || getX() < cible.getX() - 15 || getY() < cible.getY() - 15);
    }

}

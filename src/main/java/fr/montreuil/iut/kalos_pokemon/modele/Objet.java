package fr.montreuil.iut.kalos_pokemon.modele;

/**
 * interface qui permet de definir un objet qui a des positions (qui est donc visible)
 * utile pour faire les calculs de distances entre ennemi-tour-projectile
 */
public interface Objet {
    double getX();
    double getY();
}

package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class Projectile extends Attaque {

    private final Ennemi cible;

    //Degat de base = degats sans multiplicateur de type
    protected double degatsReel;

    public Projectile(Tour tour, Ennemi ennemi, EffetImpact effetImpact) {
        super(tour, effetImpact);
        this.cible = ennemi;
        this.degatsReel = Type.calculDegats(tour.getType(),ennemi.getType(),tour.getDegats());
    }

/*
    //public Projectile(Tour tour, Ennemi ennemi, Game game, ArrayList<EffetImpact> effetImpacts ) {
    public Projectile(Tour tour, Ennemi ennemi, ArrayList<EffetImpact> effetImpacts ) {
        super(tour, effetImpacts);
        cible = ennemi;
        //degatToucher = Parametres.calculDegats(tour.getType(),ennemi.getType(),tour.getDegats());
        degatDeBase = Type.calculDegats(tour.getType(),ennemi.getType(),tour.getDegats());
    }

    //public Projectile(Tour tour, Ennemi ennemi, Game game) {
    public Projectile(Tour tour, Ennemi ennemi) {
        this(tour, ennemi, (ArrayList<EffetImpact>) null); //c moche mais jsp pk je suis obliger de faire ca
        effetImpacts = new ArrayList<>();
    }

    //public Projectile(Tour tour, Ennemi cible, Game game, EffetImpact effet) {
    public Projectile(Tour tour, Ennemi cible, EffetImpact effet) {
        this(tour, cible);
        ArrayList<EffetImpact> list = new ArrayList<>();
        list.add(effet);
        this.effetImpacts = list;
    }

 */


    /**
     * methode qui permet de simuler un deplacement simple des projectiles
     * ils suivent la cible jusqu'a etre assez proche pour exploser
     */
    @Override
    public void bouge() {

        if (doitBouger()) {
            y.set(getY() < cible.getY() ? getY() + 4 : getY() - 4);
            x.set(getX() < cible.getX() ? getX() + 4 : getX() - 4);
            super.bouge();
        } else
            explotionTir();

    }

    /**
     * methode a executer a la fin de vie du projectiles
     * permet de faire subir des degats a la cible puis disparait
     */
    protected void explotionTir(){
        if (cible.getHp() > 0) {
            cible.diminueHP(degatsReel);
            tireur.ajouteDegats(degatsReel);
            ajouteEffet();
        }
        //game.remove(this);
        Game.getGame().remove(this);
    }

    private void ajouteEffet() {
        cible.ajouteEffet(this.effetImpact);
        /*
        for (EffetImpact e : effetImpacts)
            cible.ajouteEffet(e);

         */
    }

    /**
     * on estime que le projectile est arrivé lorsqu'il est a 15 pixel de la cible
     */
    private boolean doitBouger(){
        return Parametres.distance(this,cible) > 15;
    }

}

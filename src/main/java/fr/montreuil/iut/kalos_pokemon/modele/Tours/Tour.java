package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Pokemon;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import javafx.beans.property.*;

import java.util.List;

public abstract class Tour extends Pokemon implements Objet {
    private static int compteurID = 1;
    protected IntegerProperty portee;
    protected int degats;
    private final int prix;
    protected final IntegerProperty level;
    protected final DoubleProperty compteurDegats;
    protected int attaqueSpeed;
    protected int tempProchaineAttaque;
    //protected Game game;
    protected Competence myCompetence;

    public Tour(int portee, int degats, Type type, int prix, int x, int y, String pokemon, int attaqueSpeed, Competence competence) {
        super(pokemon,type,x,y);
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = new SimpleIntegerProperty(portee);
        this.degats = degats;
        this.prix = prix;
        this.level = new SimpleIntegerProperty(1);
        this.myCompetence = competence;
        this.attaqueSpeed = attaqueSpeed;
        this.compteurDegats = new SimpleDoubleProperty(0);
        tempProchaineAttaque = 0;

    }

    public int getAttaqueSpeed() {
        return attaqueSpeed;
    }

    public int getPortee() {
        return portee.get();
    }

    //public Game getGame() {return game;}

    public int getTempProchaineAttaque() {
        return tempProchaineAttaque;
    }

    public Competence getMyCompetence() {
        return myCompetence;
    }

    public void setMyCompetence(Competence myCompetence) {
        this.myCompetence = myCompetence;
    }
    public void setNom(String nouveauNom){this.nom = nouveauNom;}

    public IntegerProperty porteeProperty() {
        return portee;
    }

    public int getDegats() {
        return degats;
    }

    public int getPrix() {
        return this.prix;
    }

    public int getLevel() {
        return level.get();
    }
    public double getCompteurDegats() {
        return compteurDegats.get();
    }
    public DoubleProperty compteurDegatsProperty() {
        return compteurDegats;
    }
    public IntegerProperty levelProperty() {
        return level;
    }

    public void ajouteDegats(double value) { compteurDegats.set(compteurDegats.get() + value);}
    public void levelUp(){
        // !! l'ordre est important car il y a des listener qui sont pris en compte
        if (level.get() + 1 == Parametres.niveauEvolutionTour)
            evolution();

        this.level.set(level.get()+1);

        amelioreStats();
    };

    public void actif(){
        myCompetence.actif();
    }

    public boolean actifPret() {
        return myCompetence.isEstPretActif();
    }

    public BooleanProperty estPretActifProperty() { return myCompetence.estPretActifProperty();}

    public IntegerProperty tempProchaineActifProperty() { return myCompetence.tempProchainActifProperty();}

    protected void evolution(){
        //setNom(Parametres.nomGrandEvolution.get(nom));
        setNom(PokemonEnum.valueOf(nom).getNomEvolution());
        //myCompetence.setTempProchainActif(game.getNbFrameValue());
        myCompetence.setTempProchainActif(Game.getGame().getNbFrameValue());
    }

    public abstract void amelioreStats();

    public void attaque() {

        Ennemi cible = chercheCible();

        if (cible != null) {
            lanceProjectile(cible);
            //tempProchaineAttaque = game.getNbFrameValue() + attaqueSpeed;
            tempProchaineAttaque = Game.getGame().getNbFrameValue() + attaqueSpeed;
        }

    }

    /**
     * @return un ennemi si il y en a un en portée sinon renvoie null
     */
    protected Ennemi chercheCible() {
        Ennemi cible = null;
        int index = 0;

        //List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();
        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();

        //cherche une cible
        while (cible == null && index < listEnnemi.size()) {

            if (peutCibler(listEnnemi.get(index)))
                cible = listEnnemi.get(index);
            else
                index++;

        }
        return cible;
    }

    /**
     * methode qui est utile car il y a des tours qui ont des conditions supplementaires pour le ciblage
     */
    protected boolean peutCibler(Ennemi ennemi) {
        return estADistance(ennemi);
    }

    /**
     * @return true si l'ennemi est a une distance inferieur a la portée de la tour
     */
    public boolean estADistance(Ennemi ennemi) {
        return Parametres.distance(this,ennemi) <= portee.get();
    }

    /**
     * utile car toutes les tours ne lancent pas les meme type projectiles
     */
    protected void lanceProjectile(Ennemi cible){
        //game.ajouteProjectile(new Projectile(this, cible, game));
        Game.getGame().ajouteProjectile(new Projectile(this, cible));
    }

    public int prixRevente(){
        int sommeCumulee = (this.level.get() - 1) * this.level.get() / 2;
        return (int)( (this.prix + this.prix * (this.level.get() - 1 + sommeCumulee/10.0)) * Parametres.pourcentageRevente);
    }

    /**
     *Chaque amélioration coute 10% plus cher
     */
    public int prixAmelioration(){
        return (int)(this.prix * (1 + 0.1 * this.level.get() ) );
    }
}

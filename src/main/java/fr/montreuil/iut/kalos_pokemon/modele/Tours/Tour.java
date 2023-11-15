package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.Pokemon;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences.Competence;
import javafx.beans.property.*;

public abstract class Tour implements Objet {
    private static int compteurID = 1;
    protected IntegerProperty portee;
    protected int degats;
    protected final String type;
    private String nom;
    private final int prix;
    private final IntegerProperty x;
    private final IntegerProperty y;
    protected final IntegerProperty level;
    protected final DoubleProperty compteurDegats;
    private final String id;
    protected int attaqueSpeed;
    protected int tempProchaineAttaque;
    protected Competence myCompetence;
    protected ModeAttaque modeAttaque;
    private ForgeEffectImpact myForgeEffectImpact;

    public Tour(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed,
                Competence competence, ForgeEffectImpact myForgeEffectImpact) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = new SimpleIntegerProperty(portee);
        this.degats = degats;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.level = new SimpleIntegerProperty(1);
        this.myCompetence = competence;
        this.nom = pokemon;
        this.attaqueSpeed = attaqueSpeed;
        this.compteurDegats = new SimpleDoubleProperty(0);
        tempProchaineAttaque = 0;
        this.modeAttaque = null;
        this.myForgeEffectImpact = myForgeEffectImpact;
    }

    /** GETTER + SETTER */

    public void actif() {
        myCompetence.actif();
    }

    public boolean actifPret() {
        return myCompetence.isEstPretActif();
    }

    public IntegerProperty porteeProperty() {
        return portee;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public DoubleProperty compteurDegatsProperty() {
        return compteurDegats;
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public BooleanProperty estPretActifProperty() {
        return myCompetence.estPretActifProperty();
    }

    public IntegerProperty tempProchaineActifProperty() {
        return myCompetence.tempProchainActifProperty();
    }

    //SETERS
    public void setModeAttaque(ModeAttaque modeAttaque) {
        this.modeAttaque = modeAttaque;
    }

    public void setMyCompetence(Competence myCompetence) {
        this.myCompetence = myCompetence;
    }

    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
    }

    public int getAttaqueSpeed() {
        return attaqueSpeed;
    }

    public int getPortee() {
        return portee.get();
    }

    public int getTempProchaineAttaque() {
        return tempProchaineAttaque;
    }

    public Competence getMyCompetence() {
        return myCompetence;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public int getDegats() {
        return degats;
    }

    public int getX() {
        return x.get();
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

    public String getType() {
        return this.type;
    }

    public int getY() {
        return y.get();
    }

    /** methode abstract */

    public abstract void amelioreStats();

    /** methode public */

    public void attaque() {
        this.modeAttaque.attaque();
        tempProchaineAttaque = Game.getGame().getNbFrameValue() + attaqueSpeed;
    }

    public void ajouteDegats(double value) {
        compteurDegats.set(compteurDegats.get() + value);
    }

    public void levelUp() {
        // !! l'ordre est important car il y a des listener qui sont pris en compte
        if (level.get() + 1 == Parametres.niveauEvolutionTour)
            evolution();

        this.level.set(level.get() + 1);

        amelioreStats();
    }

    /**
     * @return true si l'ennemi est a une distance inferieur a la portée de la tour
     */
    public boolean estADistance(Ennemi ennemi) {
        return Parametres.distance(this, ennemi) <= portee.get();
    }

    public int prixRevente() {
        int sommeCumulee = (this.level.get() - 1) * this.level.get() / 2;
        return (int) ((this.prix + this.prix * (this.level.get() - 1 + sommeCumulee / 10.0)) * Parametres.pourcentageRevente);
    }

    /**
     * Chaque amélioration coute 10% plus cher
     */
    public int prixAmelioration() {
        return (int) (this.prix * (1 + 0.1 * this.level.get()));
    }

    /** methode private */

    protected void evolution() {
        setNom(Pokemon.valueOf(nom).getNomEvolution());
        myCompetence.setTempProchainActif(Game.getGame().getNbFrameValue());
    }


}

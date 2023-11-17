package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.NewCompetence;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;
import fr.montreuil.iut.kalos_pokemon.modele.Pokemon;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences.Competence;
import javafx.beans.property.*;

public abstract class Tour extends Pokemon implements Objet {
    private static int compteurID = 1;
    protected IntegerProperty portee;
    protected int degats;
    private final int prix;
    protected final IntegerProperty level;
    protected final DoubleProperty compteurDegats;
    protected int attaqueSpeed;
    protected int tempProchaineAttaque;

    protected Competence myCompetence;
    protected ModeDeCiblage modeDeCiblage;
    private ForgeEffetImpact myForgeEffetImpact;
    private ForgeEntiteAttaque myForgeEntiteAttaque;

    public Tour(int portee, int degats, Type type, int prix, int x, int y, String pokemon,
                int attaqueSpeed, Competence competence) {
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
        this.modeDeCiblage = null;
        this.myForgeEffetImpact = null;
        this.myForgeEntiteAttaque = null;
    }

    /** GETTER + SETTER */

    public IntegerProperty porteeProperty() {
        return portee;
    }

    public ForgeEffetImpact getMyForgeEffectImpact() {
        return myForgeEffetImpact;
    }

    public void setMyForgeEffectImpact(ForgeEffetImpact myForgeEffetImpact) {
        this.myForgeEffetImpact = myForgeEffetImpact;
    }

    public void setMyForgeAttaque(ForgeEntiteAttaque myForgeEntiteAttaque) {
        this.myForgeEntiteAttaque = myForgeEntiteAttaque;
    }

    //SETERS
    public void setModeAttaque(ModeDeCiblage modeDeCiblage) {
        this.modeDeCiblage = modeDeCiblage;
    }

    public void setMyCompetence(Competence myCompetence) {
        this.myCompetence = myCompetence;
    }

    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
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

    public IntegerProperty levelProperty() {
        return level;
    }

    public void ajouteDegats(double value) { compteurDegats.set(compteurDegats.get() + value);}
    public void levelUp(){
        // !! l'ordre est important car il y a des listener qui sont pris en compte
        if (level.get() + 1 == Parametres.niveauEvolutionTour)
            evolution();

        this.level.set(level.get() + 1);

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

    public abstract void amelioreStats();

    public void attaque() {
        modeDeCiblage.attaque(degats, myForgeEffetImpact, myForgeEntiteAttaque);
        tempProchaineAttaque = Game.getGame().getNbFrameValue() + attaqueSpeed;
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
        setNom(PokemonEnum.valueOf(nom).getNomEvolution());
        myCompetence.setTempProchainActif(Game.getGame().getNbFrameValue());
    }


}

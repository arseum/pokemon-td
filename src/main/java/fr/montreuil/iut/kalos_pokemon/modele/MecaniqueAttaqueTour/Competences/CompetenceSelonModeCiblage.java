package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class CompetenceSelonModeCiblage implements Competence {
    protected ModeDeCiblage myModeDeCiblage;
    protected int degats;
    protected ForgeEffetImpact myForgeEffetImpact;
    protected ForgeEntiteAttaque myForgeEntiteAttaque;
    protected IntegerProperty tempProchainActif;
    protected BooleanProperty estPretActif;
    protected Seconde cooldown;
    protected Tour myTour;

    public CompetenceSelonModeCiblage(Tour myTour, Seconde cooldown,
                                      ModeDeCiblage modeDeCiblage, int degats,
                                      ForgeEntiteAttaque forgeEntiteAttaque, ForgeEffetImpact forgeEffetImpact) {
        this.myTour = myTour;
        tempProchainActif = null;
        estPretActif = new SimpleBooleanProperty(false);
        this.cooldown = cooldown;
        this.myModeDeCiblage = modeDeCiblage;
        this.degats = degats;
        this.myForgeEntiteAttaque = forgeEntiteAttaque;
        this.myForgeEffetImpact = forgeEffetImpact;
    }

    public void activerCompetence() {
        myModeDeCiblage.attaque(degats, myForgeEffetImpact, myForgeEntiteAttaque);
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt());
    }

    public void setTempProchainActif(int t) {
        if (tempProchainActif == null)
            tempProchainActif = new SimpleIntegerProperty(t);
        else
            this.tempProchainActif.set(t);
    }

    public BooleanProperty estPretActifProperty() {
        return estPretActif;
    }

    public IntegerProperty tempProchainActifProperty() {
        return tempProchainActif;
    }

    public boolean isEstPretActif() {
        return estPretActif.get();
    }

    public void setMyModeAttaque(ModeDeCiblage myModeDeCiblage) {
        this.myModeDeCiblage = myModeDeCiblage;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public void setMyForgeEffectImpact(ForgeEffetImpact myForgeEffetImpact) {
        this.myForgeEffetImpact = myForgeEffetImpact;
    }

    public void setMyForgeAttaque(ForgeEntiteAttaque myForgeEntiteAttaque) {
        this.myForgeEntiteAttaque = myForgeEntiteAttaque;
    }
}

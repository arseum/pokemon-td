package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class CompetenceSelonModeAttaque extends ClassicCompetence {

    protected ModeAttaque myModeAttaque;
    protected int degats;
    protected ForgeEffectImpact myForgeEffectImpact;
    protected ForgeAttaque myForgeAttaque;

    public CompetenceSelonModeAttaque(Tour myTour, Seconde cooldown,
                                      ModeAttaque modeAttaque, int degats,
                                      ForgeAttaque forgeAttaque, ForgeEffectImpact forgeEffectImpact) {
        super(myTour, cooldown);
        this.myModeAttaque = modeAttaque;
        this.degats = degats;
        this.myForgeAttaque = forgeAttaque;
        this.myForgeEffectImpact = forgeEffectImpact;
    }

    public void setMyModeAttaque(ModeAttaque myModeAttaque) {
        this.myModeAttaque = myModeAttaque;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public void setMyForgeEffectImpact(ForgeEffectImpact myForgeEffectImpact) {
        this.myForgeEffectImpact = myForgeEffectImpact;
    }

    public void setMyForgeAttaque(ForgeAttaque myForgeAttaque) {
        this.myForgeAttaque = myForgeAttaque;
    }

    @Override
    public void actif() {
        System.out.println("BOUMMMMMMM");
        myModeAttaque.attaque(degats,myForgeEffectImpact,myForgeAttaque);
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt()) ;
    }
}

package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class CompetenceSelonModeAttaque extends ClassicCompetence {

    protected ModeDeCiblage myModeDeCiblage;
    protected int degats;
    protected ForgeEffectImpact myForgeEffectImpact;
    protected ForgeAttaque myForgeAttaque;

    public CompetenceSelonModeAttaque(Tour myTour, Seconde cooldown,
                                      ModeDeCiblage modeDeCiblage, int degats,
                                      ForgeAttaque forgeAttaque, ForgeEffectImpact forgeEffectImpact) {
        super(myTour, cooldown);
        this.myModeDeCiblage = modeDeCiblage;
        this.degats = degats;
        this.myForgeAttaque = forgeAttaque;
        this.myForgeEffectImpact = forgeEffectImpact;
    }

    public void setMyModeAttaque(ModeDeCiblage myModeDeCiblage) {
        this.myModeDeCiblage = myModeDeCiblage;
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
        myModeDeCiblage.attaque(degats,myForgeEffectImpact,myForgeAttaque);
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt()) ;
    }
}

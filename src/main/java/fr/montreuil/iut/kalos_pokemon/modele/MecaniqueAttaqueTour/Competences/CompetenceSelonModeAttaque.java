package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class CompetenceSelonModeAttaque extends ClassicCompetence {

    protected ModeDeCiblage myModeDeCiblage;
    protected int degats;
    protected ForgeEffetImpact myForgeEffetImpact;
    protected ForgeEntiteAttaque myForgeEntiteAttaque;

    public CompetenceSelonModeAttaque(Tour myTour, Seconde cooldown,
                                      ModeDeCiblage modeDeCiblage, int degats,
                                      ForgeEntiteAttaque forgeEntiteAttaque, ForgeEffetImpact forgeEffetImpact) {
        super(myTour, cooldown);
        this.myModeDeCiblage = modeDeCiblage;
        this.degats = degats;
        this.myForgeEntiteAttaque = forgeEntiteAttaque;
        this.myForgeEffetImpact = forgeEffetImpact;
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

    @Override
    public void actif() {
        System.out.println("BOUMMMMMMM");
        myModeDeCiblage.attaque(degats, myForgeEffetImpact, myForgeEntiteAttaque);
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt()) ;
    }
}

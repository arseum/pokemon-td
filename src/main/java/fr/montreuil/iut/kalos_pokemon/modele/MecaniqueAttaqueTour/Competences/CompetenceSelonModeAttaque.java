package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeDeCiblage;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class CompetenceSelonModeAttaque extends ClassicCompetence {

    protected ModeDeCiblage myModeDeCiblage;
    protected int degats;
    protected ForgeEffetImpact myForgeEffetImpact;
    protected ForgeEntiteDommage myForgeEntiteDommage;

    public CompetenceSelonModeAttaque(Tour myTour, Seconde cooldown,
                                      ModeDeCiblage modeDeCiblage, int degats,
                                      ForgeEntiteDommage forgeEntiteDommage, ForgeEffetImpact forgeEffetImpact) {
        super(myTour, cooldown);
        this.myModeDeCiblage = modeDeCiblage;
        this.degats = degats;
        this.myForgeEntiteDommage = forgeEntiteDommage;
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

    public void setMyForgeAttaque(ForgeEntiteDommage myForgeEntiteDommage) {
        this.myForgeEntiteDommage = myForgeEntiteDommage;
    }

    @Override
    public void actif() {
        System.out.println("BOUMMMMMMM");
        myModeDeCiblage.attaque(degats, myForgeEffetImpact, myForgeEntiteDommage);
        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt()) ;
    }
}

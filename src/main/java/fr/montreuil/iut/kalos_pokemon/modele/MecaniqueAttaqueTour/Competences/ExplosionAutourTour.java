package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommageInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ExplosionAutourTour extends CompetenceSelonModeAttaque{

//    private BooleanProperty actif;


    public ExplosionAutourTour(Tour myTour, int degats,
                               ForgeEffetImpact forgeEffetImpact) {
        super(myTour, new Seconde(20), null, degats, null, forgeEffetImpact);

        setMyModeAttaque(new ModeCiblageZone(myTour));
        setMyForgeAttaque(new ForgeEntiteDommageInstantane());

        //ici on force le faite que une explotion fase des degat direct dans la zone de
        //portee de la tour mais on laisse la possiblite de rajouter des effet a l'impact
        //a la competence
        //j'ai aussi forcé le fait que le cooldown est definie par defaut
        //FIXME MAJEUR (ars)
        // j'ai retirer tout les elements de la vue
    }

//    public BooleanProperty actifProperty() {
//        return actif;
//    }
//
//    public void activation(){
//        actif.set(!actif.get());
//    }

//    @Override
//    public void actif() {
//        //permet de provoquer une animation dans la vue
//        activation();
//
//    }
}

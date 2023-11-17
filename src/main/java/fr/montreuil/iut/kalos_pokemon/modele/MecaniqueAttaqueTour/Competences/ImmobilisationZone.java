package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage.ModeCiblageZone;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ImmobilisationZone extends CompetenceSelonModeAttaque {

    public ImmobilisationZone(Tour myTour, int dureeStun) {
        super(myTour,new Seconde(18),null,0,null,null);

        setMyForgeAttaque(new ForgeEntiteAttaqueInstantane());
        setMyModeAttaque(new ModeCiblageZone(myTour));
        setMyForgeEffectImpact(new ForgeEffetNull(myTour));
//        setMyForgeEffectImpact(new ForgeEffetImmobilisation(mytour,dureeStun));

    }

//    public void setDureeStun(int dureeStun) {
//        this.dureeStun = dureeStun;
//    }
//
//    @Override
//    public void actif() {
//        //List<Ennemi> listEnnemi = myTour.getGame().getListEnnemi().stream().toList();
//        List<Ennemi> listEnnemi = Game.getGame().getListEnnemi().stream().toList();
//
//        for (Ennemi e : listEnnemi) {
//            if (Parametres.distance(myTour,e) <= myTour.getPortee())
//                e.stun(dureeStun);
//        }
//
//        //tempProchainActif.set(myTour.getGame().getNbFrameValue() + cooldown.getTempFrameInt());
//        tempProchainActif.set(Game.getGame().getNbFrameValue() + cooldown.getTempFrameInt());
//    }



}

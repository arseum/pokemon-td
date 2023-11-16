package fr.montreuil.iut.kalos_pokemon.modele.Tours.Competences;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffetNull;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeAttaqueDirect;
import fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque.ModeZone;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;


import java.util.List;

public class ImmobilisationZone extends CompetenceSelonModeAttaque {

    public ImmobilisationZone(Tour myTour, int dureeStun) {
        super(myTour,new Seconde(18),null,0,null,null);

        setMyForgeAttaque(new ForgeAttaqueDirect());
        setMyModeAttaque(new ModeZone(myTour));
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

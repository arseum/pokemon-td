package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EnnemiTest {

    //Pas testable car la vitesseAct et max ne sont pas accessibles

    //resetVItesse
    //reduitVitesseMax
    //reduitVitesse
    @Test
    public final void diminueHPTest(){
        Game g= new Game("savane");
        Ludicolo ludicolo = new Ludicolo(0*32, 3*32, g);
        g.ajouteEnnemi(ludicolo);

        assertEquals(250, ludicolo.getHp());
        assertTrue(g.getListEnnemi().contains(ludicolo));
        assertEquals(g.getPokedollar(),850);

        ludicolo.diminueHP(249);
        assertEquals(1,ludicolo.getHp());
        assertTrue(g.getListEnnemi().contains(ludicolo));
        assertEquals(g.getPokedollar(),850);

        ludicolo.diminueHP(2);
        assertEquals( -1, ludicolo.getHp());
        assertFalse(g.getListEnnemi().contains(ludicolo));
        assertEquals(g.getPokedollar(),895);
    }
    private void parcoursToutCheminSensPositif(Ennemi e, int positionInitiale, int vitesseEnnemi, int longueurChemin, boolean selonX){
        int position = positionInitiale;
        for(int i = 0; i < longueurChemin - 1; i++){
            while((position + vitesseEnnemi) < (i+1) * Parametres.tailleTuile){
                position = position + vitesseEnnemi;
                e.bouge();
                if(selonX) assertEquals(position, e.getX());
                else assertEquals(position, e.getY());
            }
            position = Parametres.tailleTuile * (i+1);
            e.bouge();
            if(selonX) assertEquals(position, e.getX());
            else assertEquals(position, e.getY());
        }
        while ((position + vitesseEnnemi) < longueurChemin * Parametres.tailleTuile){
            position = position + vitesseEnnemi;
            e.bouge();
            if(selonX) assertEquals(position, e.getX());
            else assertEquals(position, e.getY());
        }
    }

    private void parcoursToutCheminSensNegatif(Ennemi e, int positionInitiale, int vitesseEnnemi, int longueurChemin, boolean selonX){
        int position = positionInitiale;
        for(int i = longueurChemin; i > 1; i--){
            while((position - vitesseEnnemi) > (i-1) * Parametres.tailleTuile){
                position = position - vitesseEnnemi;
                e.bouge();
                if(selonX) assertEquals(position, e.getX());
                else assertEquals(position, e.getY());
            }
            position = Parametres.tailleTuile * (i-1);
            e.bouge();
            if(selonX) assertEquals(position, e.getX());
            else assertEquals(position, e.getY());
        }
    }

    @Test
    public final void bougeTest(){
        //Cas sans stun
        Game gameDplVersBas = new Game("deplacementTestVersBas");
        Game gameDplVersHaut = new Game("deplacementTestVersHaut");
        Game gameDplVersGauche = new Game("deplacementTestVersGauche");
        Game gameDplVersDroite = new Game("deplacementTestVersDroite");

        Togepi togepi = new Togepi(4*32, 0*32, gameDplVersBas);
        parcoursToutCheminSensPositif(togepi, 0, 3, 9, false);
        assertTrue(togepi.estArrive);

        Tiplouf tiplouf = new Tiplouf(4*32, 9*32, gameDplVersHaut);
        parcoursToutCheminSensNegatif(tiplouf,9*32,4,9,false);
        assertTrue(tiplouf.estArrive);

        Camerupt camerupt = new Camerupt(0*32, 4*32, gameDplVersDroite);
        parcoursToutCheminSensPositif(camerupt, 0, 1, 9, true);
        assertTrue(camerupt.estArrive);

        Ludicolo lidiculo = new Ludicolo(9*32, 4*32, gameDplVersGauche);
        parcoursToutCheminSensNegatif(lidiculo,9*32,2,9,true);
        assertTrue(lidiculo.estArrive);

        //Cas avec stun
        Fantominus fantominus = new Fantominus(4*32, 0*32, gameDplVersBas);
        fantominus.stun(5);
        for(int i = 0; i < 5; i++){
            fantominus.bouge();
            assertEquals(0, fantominus.getY());
        }
        fantominus.bouge();
        assertEquals(3, fantominus.getY());



    }
}

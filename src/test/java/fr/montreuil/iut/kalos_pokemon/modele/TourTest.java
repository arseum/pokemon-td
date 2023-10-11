package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Poussifeu;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TourTest {
    @Test
    public final void attaqueTest(){
        Game g = new Game("deplacementTestVersBas");
        Togepi togepi = new Togepi(4*32, 0*32, g);
        Poussifeu poussifeu = new Poussifeu(5*32, 0*32);
        g.ajouteTour(poussifeu);

        poussifeu.attaque();
        //System.out.println(poussifeu.tempProchaineAttaque);
        assertTrue(g.getListProjectile().size() == 0, "Pas de projectile lancé");

        g.ajouteEnnemi(togepi);
        poussifeu.attaque();
        //System.out.println(poussifeu.tempProchaineAttaque);
        assertTrue(g.getListProjectile().size() == 1, "Un projectile lancé");

        poussifeu.attaque();
        //System.out.println(poussifeu.tempProchaineAttaque);
        assertTrue(g.getListProjectile().size() == 2, "Un deuxieme projectile lancé");
    }

    @Test
    public final void levelUpTest(){
        Tour poussifeu = new Poussifeu(0, 0);
        assertEquals(1, poussifeu.getLevel());

        poussifeu.levelUp();
        assertEquals(45, poussifeu.getDegats());
        assertEquals(44, poussifeu.getAttaqueSpeed());
        assertEquals(106, poussifeu.getPortee());
        assertEquals(2, poussifeu.getLevel());

        poussifeu.levelUp();
        assertEquals(67, poussifeu.getDegats());
        assertEquals(32, poussifeu.getAttaqueSpeed());
        assertEquals(115, poussifeu.getPortee());
        assertEquals(3, poussifeu.getLevel());
        System.out.println(poussifeu.getNom()); //todo: Pourquoi c'est null?
    }
}

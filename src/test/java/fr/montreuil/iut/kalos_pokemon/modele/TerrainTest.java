package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TerrainTest {


    @Test
    public final void estCheminTest(){
        Terrain t = new Terrain("savane");

        assertFalse(t.estChemin(0,0));
        assertFalse(t.estChemin(15,0));
        assertFalse(t.estChemin(15,31));
        assertFalse(t.estChemin(0,31));

        assertTrue(t.estChemin(3,0));
        assertTrue(t.estChemin(3,1));
        assertTrue(t.estChemin(3,2));

    }

    @Test
    public final void estDecorTest(){
        Terrain t = new Terrain("savane");

        assertTrue(t.estDecor(0,0));
        assertFalse(t.estDecor(15,0));
        assertFalse(t.estDecor(15,31));
        assertTrue(t.estDecor(0,31));

        assertTrue(t.estDecor(4,6));
        assertTrue(t.estDecor(5,7));
        assertTrue(t.estDecor(6,8));
    }
}

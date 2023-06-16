package fr.montreuil.iut.kalos_pokemon.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TerrainTest {
    @Test
    public final void estCheminTest(){
        Terrain t = new Terrain("savane");
        assertFalse(t.estChemin(0,0));
    }
}

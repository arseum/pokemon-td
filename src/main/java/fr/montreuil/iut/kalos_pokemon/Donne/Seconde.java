package fr.montreuil.iut.kalos_pokemon.Donne;

/**
 * classe qui permet d'inserer facilement des secondes grace a des methode de conversion
 */
public class Seconde {

    private final int temp;

    public Seconde(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public int getTempMs() {
        return temp*60;
    }
}

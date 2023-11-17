package fr.montreuil.iut.kalos_pokemon.Donnees;

/**
 * classe qui permet d'inserer facilement des secondes grace a des methode de conversion
 */
public class Seconde {
    private double temp;

    public Seconde(double temp) {
        this.temp = temp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTempFrameInt() {
        return (int) temp * 60;
    }

    public double getTempFrameDouble() {
        return temp * 60;
    }
}

package fr.montreuil.iut.kalos_pokemon.Donnees;

public enum Type {
    feu, eau, plante, neutre;
    private static double peuEfficace = 0.7;
    private static double superEfficace = 1.3;

    public double calculDegats(Type typeDefenseur, double degats) {
        if (this == typeDefenseur || this == Type.neutre ||
                typeDefenseur == Type.neutre)
            return degats;

        if (this.compareTo(typeDefenseur) == 1 || this.compareTo(typeDefenseur) == -2)
            return superEfficace * degats;
        else
            return peuEfficace * degats;
    }
}

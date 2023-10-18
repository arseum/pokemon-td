package fr.montreuil.iut.kalos_pokemon.Donne;

public enum Type {
    feu, plante, eau, neutre;
    private static double peuEfficace = 0.7;
    private static double superEfficace = 1.3;


    public static double calculDegats(String typeAttaquant, String typeDefenseur, int degats){
        double multiplicateur = 1;
        if(typeAttaquant.equals(Type.eau.name())){
            if(typeDefenseur.equals(Type.eau.name())||typeDefenseur.equals(Type.plante.name())){
                multiplicateur = peuEfficace;
            } else if (typeDefenseur.equals(Type.feu.name())) {
                multiplicateur = superEfficace;
            }

        } else if (typeAttaquant.equals(Type.feu.name())) {
            if(typeDefenseur.equals(Type.eau.name())||typeDefenseur.equals(Type.feu.name())){
                multiplicateur = peuEfficace;
            } else if (typeDefenseur.equals(Type.plante.name())) {
                multiplicateur = superEfficace;
            }

        } else if (typeAttaquant.equals(Type.plante.name())) {
            if(typeDefenseur.equals(Type.feu.name())||typeDefenseur.equals(Type.plante.name())){
                multiplicateur = peuEfficace;
            } else if (typeDefenseur.equals(Type.eau.name())) {
                multiplicateur = superEfficace;
            }
        }
        return degats * multiplicateur;
    }
}

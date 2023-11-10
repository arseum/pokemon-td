package fr.montreuil.iut.kalos_pokemon.Donne;

public enum Type {
    feu, eau, plante, neutre;
    private static double peuEfficace = 0.7;
    private static double superEfficace = 1.3;


    public double calculDegats(Type typeDefenseur, int degats){

        if (this == typeDefenseur || this == Type.neutre ||
            typeDefenseur == Type.neutre)
            return degats;


        if (this.compareTo(typeDefenseur) == 1 || this.compareTo(typeDefenseur) == -2)
            return superEfficace * degats;

        else
            return peuEfficace * degats;

//
//        if(typeAttaquant.equals(Type.eau.name())){
//            if(typeDefenseur.equals(Type.eau.name())||typeDefenseur.equals(Type.plante.name())){
//                multiplicateur = peuEfficace;
//            } else if (typeDefenseur.equals(Type.feu.name())) {
//                multiplicateur = superEfficace;
//            }
//
//        } else if (typeAttaquant.equals(Type.feu.name())) {
//            if(typeDefenseur.equals(Type.eau.name())||typeDefenseur.equals(Type.feu.name())){
//                multiplicateur = peuEfficace;
//            } else if (typeDefenseur.equals(Type.plante.name())) {
//                multiplicateur = superEfficace;
//            }
//
//        } else if (typeAttaquant.equals(Type.plante.name())) {
//            if(typeDefenseur.equals(Type.feu.name())||typeDefenseur.equals(Type.plante.name())){
//                multiplicateur = peuEfficace;
//            } else if (typeDefenseur.equals(Type.eau.name())) {
//                multiplicateur = superEfficace;
//            }
//        }
//        return degats * multiplicateur;
    }
}

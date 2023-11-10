package fr.montreuil.iut.kalos_pokemon.Donne;

import java.util.HashMap;
import java.util.Map;

public enum PokemonEnum {
    poussifeu(Type.feu, 60, "galifeu"),
    granivol(Type.plante, 95, "floravol"),
    grenousse(Type.eau, 155, "croaporal"),
    magneti(Type.neutre, 100, "magneton"),
    nidoran(Type.neutre, 150, "nidorino"),
    salameche(Type.feu, 130, "reptincel");

    private Type type;
    private int prix;
    private String nomEvolution;
//    public static Map<String,String> nomPetitEvolution = new HashMap<>();

    PokemonEnum(Type type, int prix, String nomEvolution) {
        this.type = type;
        this.prix = prix;
        this.nomEvolution = nomEvolution;
    }

    public Type getType() {
        return type;
    }

    public int getPrix() {
        return prix;
    }

    public String getNomEvolution() {
        return nomEvolution;
    }
}

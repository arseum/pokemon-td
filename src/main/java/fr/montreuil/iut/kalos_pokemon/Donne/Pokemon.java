package fr.montreuil.iut.kalos_pokemon.Donne;

import java.util.HashMap;
import java.util.Map;

public enum Pokemon {
    poussifeu(Type.feu, 60, "galifeu"),
    granivol(Type.plante, 95, "floravol"),
    grenousse(Type.eau, 155, "croaporal"),
    magneti(Type.neutre, 100, "magneton"),
    nidoran(Type.neutre, 150, "nidorino"),
    salameche(Type.feu, 130, "reptincel");

    private String type;
    private int prix;
    private String nomEvolution;
    public static Map<String,String> nomPetitEvolution = new HashMap<>();

    Pokemon(Type type, int prix, String nomEvolution) {
        this.type = type.name();
        this.prix = prix;
        this.nomEvolution = nomEvolution;
    }

    public String getType() {
        return type;
    }

    public int getPrix() {
        return prix;
    }

    public String getNomEvolution() {
        return nomEvolution;
    }
}

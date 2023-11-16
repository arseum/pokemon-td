package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Pokemon {
    protected final Type type;
    protected final IntegerProperty x;
    protected final IntegerProperty y;
    protected String nom;
    protected String id;

    public Pokemon(String nom, Type type, int x, int y) {
        this.nom = nom;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Type getType() {
        return type;
    }


    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }
}


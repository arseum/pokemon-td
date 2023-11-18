package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Donnees.Type;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Pokemon {
    protected final Type type;
    protected final DoubleProperty x;
    protected final DoubleProperty y;
    protected String nom;
    protected String id;

    public Pokemon(String nom, Type type, double x, double y) {
        this.nom = nom;
        this.type = type;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
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

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }
}


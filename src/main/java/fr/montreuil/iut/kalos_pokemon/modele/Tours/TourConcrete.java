package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class TourConcrete extends Tour {



    public TourConcrete(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed) {
        //je laisse un melage entre les affectation direct et les setter pour voir si il y a une difference
        setId("Tour_n°" + compteurID);
        compteurID++;
        setPorteeProperty(new SimpleIntegerProperty(portee));
        setDegats(degats);
        setType(type);
        setPrix(prix);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.level = new SimpleIntegerProperty(1);
        this.nom = pokemon;
        this.attaqueSpeed = attaqueSpeed;
        this.compteurDegats = new SimpleDoubleProperty(0);
        tempProchaineAttaque = 0;
    }

    public void evolution(){
        setNom(Parametres.nomGrandEvolution.get(nom));
    }

    @Override
    public abstract void amelioreStats();
}

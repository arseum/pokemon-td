package fr.montreuil.iut.kalos_pokemon.modele.Tours;


import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;


public interface Tour extends Objet {


     void evolution();
     String getId();
     int getPrix();
     String getNom();
     int getPortee();
     int prixRevente();
     int prixAmelioration();
     void levelUp();
     int getLevel();
     int getTempProchaineAttaque();
     void attaque();
     void setGame(Game g);
}

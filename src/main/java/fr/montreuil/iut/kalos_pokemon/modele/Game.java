package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Controlleur.ControlleurMap;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Camerupt;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ludicolo;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Tiplouf;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Grenousse;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Venalgue;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Venalgue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import java.util.Objects;

public class Game {

    private final Terrain terrain;
    /**
     * il faut que cette liste contiene tout les ennemie en fonction du - loin au + loin
     * pour l'instant tout les togepi on la meme vitesse donc ce n'est pas compliqué mais par a suite il faudra envisager
     * le fait que les ennemi peuvent se depacer les uns des autres
     */
    private final ObservableList<Ennemi> listEnnemi;
    private final ObservableList<Attaque> listProjectile;
    private final ObservableList<Tour> listTour;
    private final IntegerProperty pokedollar;
    private IntegerProperty nbFrame;
    private IntegerProperty frame = new SimpleIntegerProperty();
    private IntegerProperty cptWave = new SimpleIntegerProperty(0);
    private final IntegerProperty vie;
    private final IntegerProperty nbFrame;

    public Game() {
        terrain = new Terrain();
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
        nbFrame = new SimpleIntegerProperty(0);
    }

    public Game(String nomTerrain) {
        //todo terrain_BFS 2
        terrain = new Terrain(nomTerrain);
        listEnnemi = FXCollections.observableArrayList();
        listTour = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        pokedollar = new SimpleIntegerProperty(300);
        nbFrame = new SimpleIntegerProperty(0);
        vie = new SimpleIntegerProperty(32);
    }

    public Game() {
        this("default");
    }
    //todo 1

    public final int getFrame(){return frame.getValue();}
    public final void setFrame(int i){frame.setValue(i);}
    public final IntegerProperty frameProperty(){return frame;}



    public final int getWave(){return cptWave.getValue();}
    public final void setWave(int i){cptWave.setValue(i);}
    public final IntegerProperty cptWaveProperty(){return cptWave;}

    public Terrain getTerrain() {
        return terrain;
    }
    public IntegerProperty PokedollarProperty() {
        return pokedollar;
    }

    public int getVie() {
        return vie.get();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }

    public int getPokedollar() {
        return pokedollar.get();
    }

    public IntegerProperty nbFrameProperty() {
        return nbFrame;
    }

    public int getNbFrame() {
        return nbFrame.get();
    }

    public void ajoutePokedollar(int value) {
        pokedollar.setValue(pokedollar.get() + value);
    }

    public void perdVie(int value) {
        vie.set(vie.get() - value);
    }

    public void ajouteEnnemi(Ennemi e) {
        this.listEnnemi.add(e);
    }
    public void ajouteTour(Tour t) {
        listTour.add(t);
        t.setGame(this);
    }

    public void ajouteProjectile(Attaque a) {
        listProjectile.add(a);
    }

    public ObservableList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }

    public boolean tourSurMemePosition(int x, int y) {
        for (Tour t : this.getListTour()) {
            if (t.getX() / Parametres.tailleTuile == x / Parametres.tailleTuile && t.getY() / Parametres.tailleTuile == y / Parametres.tailleTuile) {
                return true;
            }

        }
        return false;
    }

    public boolean tourAchetable(Tour t) {
        return t.getPrix() <= this.pokedollar.get();
    }

    public boolean tourAchetable(String nomTour) {
        return (Parametres.prixTour(nomTour) != -1) && (Parametres.prixTour(nomTour) <= this.pokedollar.get());
    }

    public ObservableList<Tour> getListTour() {
        return listTour;
    }

    public ObservableList<Attaque> getListProjectile() {
        return listProjectile;
    }

    public void remove(Projectile p) {
        listProjectile.remove(p);
    }

    public void remove(Ennemi e) {
        listEnnemi.remove(e);
    }

    /**
     * methode appelée a chaque frame
     * utilisé notament pour les deplacements
     */
    public void uneFrame() {

        for (int i = listEnnemi.size() - 1; i >= 0; i--) {
            listEnnemi.get(i).seDeplace();
        }

        for (int i = listProjectile.size() - 1; i >= 0; i--) {
            if (listProjectile.get(i) instanceof Zone && ((Zone) listProjectile.get(i)).isActif())
                listProjectile.get(i).bouge();
            else
                listProjectile.get(i).bouge();
        }

        for (Tour t : listTour) {
            if (getNbFrame() >= t.tempProchaineAttaque)
                t.attaque();
            if (t instanceof Venalgue && getNbFrame() % 20 == 0)
                ((Venalgue) t).apliquePoison();
        }

    }


public void wave() throws InterruptedException {
        int[] caseDepart = terrain.caseDepart();

        if ( getNbFrame()%90==0 && getNbFrame()>=300 && getNbFrame()<900){
            if (getNbFrame()==600) setWave(getWave()+1);

            listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this));  //WAVE 1

        }
        else if (getNbFrame()>=1500 && getNbFrame()<2100 && getNbFrame()%60==0) {
            if (getNbFrame()==1200) setWave(getWave()+1);
            listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 2 attente de 5s
        }
        else if (getNbFrame()>=2700 && getNbFrame()<3300 && getNbFrame()%90==0) {
            if (getNbFrame()==1200) setWave(getWave()+1);
            listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 3 attente de 5s
        }
        else if (getNbFrame()>3900 && getNbFrame()<=4500 && getNbFrame()%90==0) {
            if (getNbFrame()==1200) setWave(getWave()+1);
            listEnnemi.add(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 3 attente de 5s
        }



}


}

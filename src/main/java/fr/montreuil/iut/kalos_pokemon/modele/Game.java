package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Controlleur.ControlleurMap;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
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
    private IntegerProperty cptWave = new SimpleIntegerProperty(1);
    private final IntegerProperty vie;



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
        int[] caseDepart = terrain.getCaseDepart();
        int frameAct = getNbFrame();

        if ( frameAct>=300 && frameAct<900){
            if (frameAct==600){
                setWave(1);
                listEnnemi.add(new Boss(caseDepart[0]*32, caseDepart[1]*32,this ));

            }

          //  if (frameAct%90==0 )
               // listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this));  //WAVE 1

        }

        else if (frameAct>=1500 && frameAct<2400 && frameAct%60==0) {
            if (frameAct==1500) setWave(getWave()+1);
            listEnnemi.add(new Roucool(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 2 attente de 5s
        }

        else if (frameAct>=2700 && frameAct<3300 && frameAct%90==0) {
            if (frameAct==2700) setWave(getWave()+1);
            listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 3 attente de 5s
        }

        else if (frameAct>=3900 && frameAct<=4500  ) {
            if (frameAct==3900) setWave(getWave()+1);
            if (frameAct%90==0){
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 4 attente de 5s

            }
        }

        else if (frameAct>5100 && frameAct<=5700 ) {
            if (frameAct==5101) setWave(getWave()+1);

            if (frameAct%90==0){
                listEnnemi.add(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, this)); // WAVE 5 attente de 5s
            }
            if (frameAct%70==0) {
                listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this));
            }

        }

    //PAUSE 15s

        else if (frameAct>6600 && frameAct<=7100 ) {
            if (frameAct == 6601) setWave(getWave() + 1);

            if (frameAct % 40 == 0) {
                listEnnemi.add(new Togepi(caseDepart[0] * 32, caseDepart[1] * 32, this));// WAVE 6

            }
            if (frameAct % 90 == 0) {
                listEnnemi.add(new Camerupt(caseDepart[0] * 32, caseDepart[1] * 32, this));
            }
        }

        else if (frameAct>7700 && frameAct<=8300 ) {
            if (frameAct==7701) setWave(getWave()+1);

            if ( frameAct%45==0){
                listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 7
            }

            if (frameAct%90==0) {
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }

        else if (frameAct>8900 && frameAct<=9500 ) {
            if (frameAct==8901) setWave(getWave()+1);

            if ( frameAct%40==0){
                listEnnemi.add(new Roucool(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 8
            }

            if (frameAct %90==0) {
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }
        else if (frameAct > 10100 && getNbFrame()<=10700 ) {            // WAVE 9
            if (frameAct == 10101) setWave(getWave()+1);

            if ( frameAct %45==0){
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }

            if (frameAct %90==0) {
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }


        //Pause 15s



        else if (frameAct >11300 && frameAct<=11900 ) {
            if (frameAct ==11301) setWave(getWave()+1);

            if ( frameAct %30==0){
                listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 10
            }

            if (frameAct %90==0) {
                listEnnemi.add(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if (getNbFrame()%150==0) {
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }


        else if (frameAct >12500 && frameAct <=13100 ) {
            if (frameAct==12501) setWave(getWave()+1);

            if ( frameAct %25==0){
                listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 11
            }

            if (frameAct %150==0) {
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }

        else if (frameAct >13700 && frameAct<=14300 ) {
            if (frameAct ==13701) setWave(getWave()+1);

            if ( frameAct %45==0){
                listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 12
            }

            if (frameAct %80==0) {
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if (getNbFrame()%130==0) {
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }

        else if (frameAct >14900 && frameAct<=15500 ) {
            if (frameAct ==14901) setWave(getWave()+1);

            if ( frameAct %70==0){
                listEnnemi.add(new Roucool(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 13
            }
            if ( frameAct %80==0){
                listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this));
            }

            if (frameAct %100==0) {
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if (getNbFrame()%140==0) {
                listEnnemi.add(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }

        else if (frameAct >15400 && frameAct<=16000 ) {
            if (frameAct ==15401) setWave(getWave()+1);

            if ( frameAct %50==0){
                listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 14
            }
            if ( frameAct %70==0){
                listEnnemi.add(new Roucool(caseDepart[0]*32, caseDepart[1]*32, this));
            }

            if (frameAct %90==0) {
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if (getNbFrame()%130==0) {
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this));
            }
        }
        else if (frameAct >16600 && frameAct<=17600 ) {
            if (frameAct ==16601) setWave(getWave()+1);

            if ( frameAct %70==0){
                listEnnemi.add(new Togepi(caseDepart[0]*32, caseDepart[1]*32, this));// WAVE 15
            }
            if ( frameAct %80==0){
                listEnnemi.add(new Roucool(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if ( frameAct %90==0){
                listEnnemi.add(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, this));
            }

            if (frameAct %100==0) {
                listEnnemi.add(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if (getNbFrame()%110==0) {
                listEnnemi.add(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, this));
            }
            if (getNbFrame()%120==0) {
                listEnnemi.add(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, this));
            }

        }

        else if (frameAct == 18400){
            listEnnemi.add(new Boss(caseDepart[0]*32, caseDepart[1]*32,this ));
        }

}


}

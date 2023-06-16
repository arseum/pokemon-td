package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Wave {
    private IntegerProperty nbFrame;
    private IntegerProperty cptWave = new SimpleIntegerProperty(1);
    private Terrain terrain;
    private Game game;
    private BooleanProperty gameGagnee = new SimpleBooleanProperty(false);



    public Wave(Terrain terrain, Game g) {
        nbFrame = new SimpleIntegerProperty(0);
        this.terrain= terrain;
        this.game = g;
    }

    public void setGagne(boolean gagne) {
        this.gameGagnee.set(gagne);
    }

    public boolean gameGagnee() {
        return gameGagnee.get();
    }

    public BooleanProperty gagneProperty() {
        return gameGagnee;
    }

    public IntegerProperty nbFrameProperty() {
        return nbFrame;
    }

    public int getNbFrame() {
        return nbFrame.get();
    }

    public final int getWave(){return cptWave.getValue();}
    public final void setWave(int i){cptWave.setValue(i);}
    public final IntegerProperty cptWaveProperty(){return cptWave;}

    private int caseDepartYVol(){
        int delta = 2;
        int min = delta;
        int max = (terrain.getHauteurTerrain()/Parametres.tailleTuile - 1) - delta;
        return min + (int)(Math.random() * ((max - min) + 1));
        //return  1 + (int)(Math.random() * ((terrain.getHauteurTerrain()/Parametres.tailleTuile - 1 - 1) + 1));

        //return (int)(Math.random() * terrain.getHauteurTerrain()/ Parametres.tailleTuile);
    }

    public void wave() throws InterruptedException {
        int[] caseDepart = terrain.getCaseDepart();
        int frameAct = getNbFrame();

        if ( frameAct>=300 && frameAct<900){
            if (frameAct==600){
                setWave(1);
            }
            if (frameAct%90==0 )
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));  //WAVE 1

        }

        else if (frameAct>=1500 && frameAct<2400 ) {
            if (frameAct==1500) setWave(getWave()+1);
            if (frameAct%400==0){
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 2

            }
        }

        else if (frameAct>=2700 && frameAct<3300 && frameAct%90==0) {
            if (frameAct==2700) setWave(getWave()+1);
            game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 3 attente de 5s
        }

        else if (frameAct>=3900 && frameAct<=4500 && frameAct%60==0 ) {
            if (frameAct==3900) setWave(getWave()+1);
            game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepartYVol()*32, game)); // WAVE4 attente de 5s

        }

        else if (frameAct>6000 && frameAct<=6600 ) {
            if (frameAct==6001) setWave(getWave()+1);

            if (frameAct%120==0){
                game.ajouteEnnemi(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 5 attente de 5s
            }
            if (frameAct%90==0) {
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));
            }

        }

        //PAUSE 15s

        else if (frameAct>7500 && frameAct<=8000 ) {
            if (frameAct == 7501) setWave(getWave() + 1);

            if (frameAct % 50 == 0) {
                game.ajouteEnnemi(new Togepi(caseDepart[0] * 32, caseDepart[1] * 32, game));// WAVE 6

            }
            if (frameAct % 100 == 0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0] * 32, caseDepart[1] * 32, game));
            }
        }

        else if (frameAct>8600 && frameAct<=9200 ) {
            if (frameAct==8601) setWave(getWave()+1);

            if ( frameAct%60==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 7
            }

            if (frameAct%120==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }

        else if (frameAct>9800 && frameAct<=10400 ) {
            if (frameAct==9801) setWave(getWave()+1);

            if ( frameAct%40==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepartYVol()*32, game));// WAVE 8
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }
        else if (frameAct > 11000 && getNbFrame()<=11600 ) {            // WAVE 9
            if (frameAct == 11001) setWave(getWave()+1);

            if ( frameAct %45==0){
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }


        //Pause 15s



        else if (frameAct >12200 && frameAct<=12800 ) {
            if (frameAct ==12201) setWave(getWave()+1);

            if ( frameAct %30==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 10
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%150==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }


        else if (frameAct >13400 && frameAct <=14000 ) {
            if (frameAct==13401) setWave(getWave()+1);

            if ( frameAct %25==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 11
            }

            if (frameAct %150==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }

        else if (frameAct >14600 && frameAct<=15100 ) {
            if (frameAct ==14601) setWave(getWave()+1);

            if ( frameAct %45==0){
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 12
            }

            if (frameAct %80==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%130==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }

        else if (frameAct >15800 && frameAct<=16400 ) {
            if (frameAct ==15801) setWave(getWave()+1);

            if ( frameAct %70==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepartYVol()*32, game));// WAVE 13
            }
            if ( frameAct %80==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));
            }

            if (frameAct %100==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%140==0) {
                game.ajouteEnnemi(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }

        else if (frameAct >16300 && frameAct<=16900 ) {
            if (frameAct ==16301) setWave(getWave()+1);

            if ( frameAct %50==0){
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 14
            }
            if ( frameAct %70==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepartYVol()*32, game));
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%130==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }
        else if (frameAct >17500 && frameAct<=18500 ) {
            if (frameAct ==17501) setWave(getWave()+1);

            if ( frameAct %70==0){
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 15
            }
            if ( frameAct %80==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepartYVol()*32, game));
            }
            if ( frameAct %90==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));
            }

            if (frameAct %100==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%110==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%120==0) {
                game.ajouteEnnemi(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, game));
            }

        }

        else if (frameAct == 19300){
            game.ajouteEnnemi(new Boss(caseDepart[0]*32, caseDepart[1]*32,game));
        } else if (frameAct > 19300 && game.bossEstVaincu()) { setGagne(true); }

    }

}

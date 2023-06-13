package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Wave {
    private IntegerProperty nbFrame;
    private IntegerProperty cptWave = new SimpleIntegerProperty(1);
    private Terrain terrain;
    private Game game;
    public Wave(Terrain terrain, Game g) {
        nbFrame = new SimpleIntegerProperty(0);
        this.terrain= terrain;
        this.game = g;
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


    public void wave() throws InterruptedException {
        int[] caseDepart = terrain.getCaseDepart();
        int frameAct = getNbFrame();

        if ( frameAct>=10 && frameAct<900){
            if (frameAct==600){
                setWave(1);
            }
            if (frameAct%90==0 )
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));  //WAVE 1

        }

        else if (frameAct>=1500 && frameAct<2400 && frameAct%60==0) {
            if (frameAct==1500) setWave(getWave()+1);
            game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 2 attente de 5s
        }

        else if (frameAct>=2700 && frameAct<3300 && frameAct%90==0) {
            if (frameAct==2700) setWave(getWave()+1);
            game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 3 attente de 5s
        }

        else if (frameAct>=3900 && frameAct<=4500  ) {
            if (frameAct==3900) setWave(getWave()+1);
            if (frameAct%90==0){
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 4 attente de 5s

            }
        }

        else if (frameAct>5100 && frameAct<=5700 ) {
            if (frameAct==5101) setWave(getWave()+1);

            if (frameAct%90==0){
                game.ajouteEnnemi(new Ludicolo(caseDepart[0]*32, caseDepart[1]*32, game)); // WAVE 5 attente de 5s
            }
            if (frameAct%70==0) {
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));
            }

        }

        //PAUSE 15s

        else if (frameAct>6600 && frameAct<=7100 ) {
            if (frameAct == 6601) setWave(getWave() + 1);

            if (frameAct % 40 == 0) {
                game.ajouteEnnemi(new Togepi(caseDepart[0] * 32, caseDepart[1] * 32, game));// WAVE 6

            }
            if (frameAct % 90 == 0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0] * 32, caseDepart[1] * 32, game));
            }
        }

        else if (frameAct>7700 && frameAct<=8300 ) {
            if (frameAct==7701) setWave(getWave()+1);

            if ( frameAct%45==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 7
            }

            if (frameAct%90==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }

        else if (frameAct>8900 && frameAct<=9500 ) {
            if (frameAct==8901) setWave(getWave()+1);

            if ( frameAct%40==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 8
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }
        else if (frameAct > 10100 && getNbFrame()<=10700 ) {            // WAVE 9
            if (frameAct == 10101) setWave(getWave()+1);

            if ( frameAct %45==0){
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }


        //Pause 15s



        else if (frameAct >11300 && frameAct<=11900 ) {
            if (frameAct ==11301) setWave(getWave()+1);

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


        else if (frameAct >12500 && frameAct <=13100 ) {
            if (frameAct==12501) setWave(getWave()+1);

            if ( frameAct %25==0){
                game.ajouteEnnemi(new Tiplouf(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 11
            }

            if (frameAct %150==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }

        else if (frameAct >13700 && frameAct<=14300 ) {
            if (frameAct ==13701) setWave(getWave()+1);

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

        else if (frameAct >14900 && frameAct<=15500 ) {
            if (frameAct ==14901) setWave(getWave()+1);

            if ( frameAct %70==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 13
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

        else if (frameAct >15400 && frameAct<=16000 ) {
            if (frameAct ==15401) setWave(getWave()+1);

            if ( frameAct %50==0){
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 14
            }
            if ( frameAct %70==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepart[1]*32, game));
            }

            if (frameAct %90==0) {
                game.ajouteEnnemi(new Fantominus(caseDepart[0]*32, caseDepart[1]*32, game));
            }
            if (getNbFrame()%130==0) {
                game.ajouteEnnemi(new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game));
            }
        }
        else if (frameAct >16600 && frameAct<=17600 ) {
            if (frameAct ==16601) setWave(getWave()+1);

            if ( frameAct %70==0){
                game.ajouteEnnemi(new Togepi(caseDepart[0]*32, caseDepart[1]*32, game));// WAVE 15
            }
            if ( frameAct %80==0){
                game.ajouteEnnemi(new Roucool(caseDepart[0]*32, caseDepart[1]*32, game));
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

        else if (frameAct == 18400){
            game.ajouteEnnemi(new Boss(caseDepart[0]*32, caseDepart[1]*32,game));
        }

    }

}

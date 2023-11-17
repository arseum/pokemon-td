package fr.montreuil.iut.kalos_pokemon.modele.Map;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues.FabriqueVague;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues.Vague;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues.VagueBoss;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues.VagueComposee;

import java.util.ArrayList;

public class ConstructeurParNiveau {

    public static ArrayList<Vague> getTouteWaveNiveau(Terrain niveau){
        return switch (niveau.getNom()) {
            case "savane" -> mapSavane(niveau);
            case "neige" -> mapNeige(niveau);
            case "eau" -> mapEau(niveau);
            default -> null;
        };

    }

    /**
     * duree : 10min
     */
    private static ArrayList<Vague> mapSavane(Terrain niveau){
        FabriqueVague fabVag= FabriqueVague.getInstance();
        ArrayList<Vague> list = new ArrayList<>();

        list.add(fabVag.creeVagueTogepi(niveau,new Seconde(15),new Seconde(1.6)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(28),
                new ArrayList<>(){{
                    add(fabVag.creeVagueTogepi(niveau,new Seconde(10),new Seconde(2)));
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(15),new Seconde(5)));
                }}
        ));

        list.add(fabVag.creeVagueCamerupt(niveau,new Seconde(39),new Seconde(10)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(70),
                new ArrayList<>(){{
                    add(fabVag.creeVagueCamerupt(niveau,new Seconde(59),new Seconde(30)));
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(59),new Seconde(4)));
                }}
        ));

        list.add(new VagueComposee(
                niveau,
                new Seconde(90),
                new ArrayList<>(){{
                    add(fabVag.creeVagueCamerupt(niveau,new Seconde(79),new Seconde(27)));
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(79),new Seconde(3)));
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(30),new Seconde(2.6)));
                    add(fabVag.creeVagueTogepi(niveau,new Seconde(20),new Seconde(2)));
                }}
        ));

        list.add(fabVag.creeVagueRoucoul(niveau,new Seconde(40),new Seconde(2.7)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(60),
                new ArrayList<>(){{
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(44),new Seconde(1.9)));
                    add(fabVag.creeVagueRoucoul(niveau,new Seconde(44),new Seconde(2.1)));
                }}
        ));

        list.add(new VagueComposee(
                niveau,
                new Seconde(65),
                new ArrayList<>(){{
                    add(fabVag.creeVagueTogepi(niveau,new Seconde(50),new Seconde(1.3)));
                    add(fabVag.creeVagueTiplouf(niveau,new Seconde(45),new Seconde(3.5)));
                    add(fabVag.creeVagueCamerupt(niveau,new Seconde(50),new Seconde(8)));
                }}
        ));

        list.add(new VagueComposee(
                niveau,
                new Seconde(75),
                new ArrayList<>(){{
                    add(fabVag.creeVagueRoucoul(niveau,new Seconde(30),new Seconde(1.7)));
                    add(fabVag.creeVagueTiplouf(niveau,new Seconde(60),new Seconde(3)));
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(60),new Seconde(1.4)));
                    add(fabVag.creeVagueCamerupt(niveau,new Seconde(60),new Seconde(15)));
                }}
        ));

        list.add(fabVag.creeVagueLudicolo(niveau,new Seconde(20),new Seconde(7)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(95),
                new ArrayList<>(){{
                    add(fabVag.creeVagueRoucoul(niveau,new Seconde(80),new Seconde(2)));
                    add(fabVag.creeVagueTiplouf(niveau,new Seconde(80),new Seconde(2)));
                    add(fabVag.creeVagueFantominus(niveau,new Seconde(80),new Seconde(1.3)));
                    add(fabVag.creeVagueCamerupt(niveau,new Seconde(80),new Seconde(3)));
                    add(fabVag.creeVagueLudicolo(niveau,new Seconde(80),new Seconde(10)));
                }}
        ));

        list.add(fabVag.creeVagueTogepi(niveau,new Seconde(20),new Seconde(0.2)));

        list.add(new VagueBoss(niveau,new Seconde(10)));


        return list;
    }

    private static ArrayList<Vague> mapNeige(Terrain niveau){
        return mapSavane(niveau);
    }

    private static ArrayList<Vague> mapEau(Terrain niveau){
        return mapSavane(niveau);
    }
}

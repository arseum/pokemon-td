package fr.montreuil.iut.kalos_pokemon.modele.Map;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues.FabriqueVague;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues.Vague;
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

    private static ArrayList<Vague> mapSavane(Terrain niveau){
        FabriqueVague fabVag= FabriqueVague.getInstance();
        ArrayList<Vague> list = new ArrayList<>();

        list.add(fabVag.créeVagueTogepi(niveau,new Seconde(15),new Seconde(1.5)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(20),
                new ArrayList<>(){{
                    add(fabVag.créeVagueTogepi(niveau,new Seconde(10),new Seconde(2)));
                    add(fabVag.créeVagueFantominus(niveau,new Seconde(15),new Seconde(5)));
                }}
        ));

        list.add(fabVag.créeVagueCamerupt(niveau,new Seconde(39),new Seconde(10)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(60),
                new ArrayList<>(){{
                    add(fabVag.créeVagueCamerupt(niveau,new Seconde(59),new Seconde(30)));
                    add(fabVag.créeVagueFantominus(niveau,new Seconde(59),new Seconde(4)));
                }}
        ));

        list.add(new VagueComposee(
                niveau,
                new Seconde(80),
                new ArrayList<>(){{
                    add(fabVag.créeVagueCamerupt(niveau,new Seconde(79),new Seconde(27)));
                    add(fabVag.créeVagueFantominus(niveau,new Seconde(79),new Seconde(3)));
                    add(fabVag.créeVagueFantominus(niveau,new Seconde(30),new Seconde(2.6)));
                    add(fabVag.créeVagueTogepi(niveau,new Seconde(20),new Seconde(2)));
                }}
        ));

        list.add(fabVag.créeVagueRoucoul(niveau,new Seconde(40),new Seconde(3.3)));

        list.add(new VagueComposee(
                niveau,
                new Seconde(45),
                new ArrayList<>(){{
                    add(fabVag.créeVagueFantominus(niveau,new Seconde(44),new Seconde(1.9)));
                    add(fabVag.créeVagueRoucoul(niveau,new Seconde(44),new Seconde(2.1)));
                }}
        ));

        return list;
    }

    private static ArrayList<Vague> mapNeige(Terrain niveau){
        return mapSavane(niveau);
    }

    private static ArrayList<Vague> mapEau(Terrain niveau){
        return mapSavane(niveau);
    }
}

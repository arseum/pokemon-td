package fr.montreuil.iut.kalos_pokemon;

import java.io.*;
import java.nio.file.*;

public class Progression {

    private static final Path FICHIER = Paths.get(System.getProperty("user.home"), ".pokemon-td-progress");

    public static int niveauMaxDebloque() {
        try {
            if (Files.exists(FICHIER)) {
                String contenu = Files.readString(FICHIER).trim();
                return Integer.parseInt(contenu);
            }
        } catch (IOException | NumberFormatException e) {
            // fichier corrompu → on repart de 1
        }
        return 1;
    }

    public static void debloquerNiveau(int n) {
        if (n > niveauMaxDebloque()) {
            try {
                Files.writeString(FICHIER, String.valueOf(n));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean estDebloque(int n) {
        return niveauMaxDebloque() >= n;
    }
}

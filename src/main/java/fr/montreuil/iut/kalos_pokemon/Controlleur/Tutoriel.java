package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Tutoriel {

    private final List<EtapeTutoriel> etapes;
    private int etapeCourante;
    private final Game game;
    private final Pane pane;
    private final Timeline gameLoop;
    private final IntegerProperty frame;
    private final Runnable pauseJeu;
    private final Runnable reprendreJeu;
    private StackPane overlay;
    private boolean termine;

    public Tutoriel(Game game, Pane pane, Timeline gameLoop, IntegerProperty frame,
                    Runnable pauseJeu, Runnable reprendreJeu) {
        this.game = game;
        this.pane = pane;
        this.gameLoop = gameLoop;
        this.frame = frame;
        this.pauseJeu = pauseJeu;
        this.reprendreJeu = reprendreJeu;
        this.etapes = new ArrayList<>();
        this.etapeCourante = 0;
        this.termine = false;

        creerEtapes();
    }

    private void creerEtapes() {
        // Etape 1 : Bienvenue
        etapes.add(new EtapeTutoriel(
                "Bienvenue, dresseur !\nTon objectif : empêcher les Pokémon sauvages\nd'atteindre la fin du chemin !",
                true, null
        ));

        // Etape 2 : Acheter une tour
        etapes.add(new EtapeTutoriel(
                "Achète ta première tour !\nClique sur un Pokémon dans le menu en bas.",
                true, () -> game.getListTour().size() > 0
        ));

        // Etape 3 : Placer la tour — sera complétée en même temps que l'étape 2
        // car l'achat+placement sont liés. On détecte via listTour.
        // En fait l'étape 2 attend la sélection, l'étape 3 attend le placement.
        // Mais la sélection n'est pas facilement observable ici.
        // Simplifions : étape 2 = message bloquant "achète et place", condition = tour placée
        // Non, gardons 2 étapes distinctes pour la pédagogie.

        // Etape 3 : Place-la
        etapes.add(new EtapeTutoriel(
                "Bien ! Maintenant place-la sur le terrain.\nClique sur une case verte pour la poser.",
                false, () -> game.getListTour().size() > 0
        ));

        // Etape 4 : Les ennemis arrivent
        etapes.add(new EtapeTutoriel(
                "Parfait ! Les ennemis vont bientôt arriver.\nPrépare-toi !",
                true, null
        ));

        // Etape 5 : Info tour (non-bloquant)
        etapes.add(new EtapeTutoriel(
                "Astuce : clique sur une tour placée\npour voir ses infos !",
                false, null
        ));

        // Etape 6 : Améliorer/Vendre (non-bloquant)
        etapes.add(new EtapeTutoriel(
                "Tu peux améliorer ou vendre tes tours\ndepuis le panneau de droite. Bonne chance !",
                false, null
        ));
    }

    public void demarrer() {
        pauseJeu.run();
        afficherEtape();
    }

    private void afficherEtape() {
        if (etapeCourante >= etapes.size()) {
            terminer();
            return;
        }

        EtapeTutoriel etape = etapes.get(etapeCourante);

        if (etape.bloquante) {
            pauseJeu.run();
        }

        creerOverlay(etape);
    }

    private void creerOverlay(EtapeTutoriel etape) {
        supprimerOverlay();

        overlay = new StackPane();
        overlay.setId("tutorielOverlay");
        overlay.setMouseTransparent(false);
        overlay.setPrefWidth(pane.getPrefWidth());
        overlay.setPrefHeight(80);
        overlay.setLayoutX(0);
        overlay.setLayoutY(pane.getPrefHeight() > 0 ? pane.getPrefHeight() - 90 : 422);
        overlay.setAlignment(Pos.CENTER);

        HBox conteneur = new HBox();
        conteneur.getStyleClass().add("tutoriel-bulle");
        conteneur.setAlignment(Pos.CENTER_LEFT);
        conteneur.setPadding(new Insets(10, 20, 10, 20));
        conteneur.setSpacing(15);
        conteneur.setPrefWidth(pane.getPrefWidth() > 0 ? pane.getPrefWidth() - 40 : 984);

        Label texte = new Label(etape.message);
        texte.getStyleClass().add("tutoriel-texte");
        texte.setWrapText(true);
        texte.setMaxWidth(800);

        HBox.setHgrow(texte, javafx.scene.layout.Priority.ALWAYS);

        if (etape.bloquante && etape.condition == null) {
            // Message simple avec bouton Suivant
            Button suivant = new Button("Suivant >");
            suivant.getStyleClass().add("tutoriel-bouton");
            suivant.setOnAction(e -> etapeSuivante());
            conteneur.getChildren().addAll(texte, suivant);
            overlay.setMouseTransparent(false);
        } else if (etape.bloquante && etape.condition != null) {
            // Message bloquant qui attend une action du joueur
            // La gameLoop reste stoppée (pas de vagues ni de déplacements)
            // mais les événements souris (achat/placement de tour) fonctionnent quand même
            conteneur.getChildren().add(texte);
            overlay.setMouseTransparent(true);
        } else {
            // Non-bloquant : message affiché quelques secondes puis disparaît
            conteneur.getChildren().add(texte);
            overlay.setMouseTransparent(true);

            Timeline disparition = new Timeline(new KeyFrame(
                    Duration.seconds(5),
                    e -> {
                        supprimerOverlay();
                        etapeCourante++;
                        if (etapeCourante < etapes.size()) {
                            EtapeTutoriel prochaine = etapes.get(etapeCourante);
                            if (prochaine.bloquante || prochaine.condition != null) {
                                afficherEtape();
                            } else {
                                // Chaîner les messages non-bloquants avec un délai
                                Timeline delai = new Timeline(new KeyFrame(
                                        Duration.seconds(3), ev -> afficherEtape()
                                ));
                                delai.play();
                            }
                        } else {
                            terminer();
                        }
                    }
            ));
            disparition.play();
        }

        overlay.getChildren().add(conteneur);
        pane.getChildren().add(overlay);
    }

    public void verifier() {
        if (termine || etapeCourante >= etapes.size()) return;

        EtapeTutoriel etape = etapes.get(etapeCourante);
        if (etape.condition != null && etape.condition.getAsBoolean()) {
            etapeSuivante();
        }
    }

    public void notifierTourAchetee() {
        if (termine || etapeCourante >= etapes.size()) return;
        // Étape 2 (index 1) : attend l'achat d'une tour dans le shop
        if (etapeCourante == 1) {
            // Passer à l'étape "place-la"
            etapeCourante++;
            afficherEtape();
        }
    }

    public void notifierTourPlacee() {
        if (termine || etapeCourante >= etapes.size()) return;
        // Étape 3 (index 2) : attend le placement
        if (etapeCourante == 2) {
            etapeSuivante();
        }
    }

    private void etapeSuivante() {
        etapeCourante++;
        supprimerOverlay();

        if (etapeCourante == 4) {
            // Après étape 4 (index 3, "les ennemis arrivent"), on lance le jeu
            // et on remet le compteur de frames à 0
            frame.set(0);
            reprendreJeu.run();
            // Les étapes 5 et 6 sont non-bloquantes, affichées avec délai
            Timeline delai = new Timeline(new KeyFrame(
                    Duration.seconds(8), e -> afficherEtape()
            ));
            delai.play();
            return;
        }

        afficherEtape();
    }

    private void supprimerOverlay() {
        if (overlay != null) {
            pane.getChildren().remove(overlay);
            overlay = null;
        }
    }

    private void terminer() {
        supprimerOverlay();
        termine = true;
    }

    public boolean estTermine() {
        return termine;
    }

    public int getEtapeCourante() {
        return etapeCourante;
    }

    private static class EtapeTutoriel {
        final String message;
        final boolean bloquante;
        final BooleanSupplier condition;

        EtapeTutoriel(String message, boolean bloquante, BooleanSupplier condition) {
            this.message = message;
            this.bloquante = bloquante;
            this.condition = condition;
        }
    }
}

package fr.montreuil.iut.kalos_pokemon;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SoundManager {

    private static AudioClip clipAchat;
    private static AudioClip clipKill;
    private static AudioClip clipPerdVie;
    private static AudioClip clipFinVague;
    private static AudioClip clipVictoire;
    private static AudioClip clipDefaite;
    private static AudioClip clipJingle;
    private static boolean initialized = false;

    private static final float SAMPLE_RATE = 44100f;

    public static void init() {
        if (initialized) return;
        try {
            clipAchat = createClip(generateTone(880, 100, 0.5));
            clipKill = createClip(generateSweep(440, 220, 80, 0.4));
            clipPerdVie = createClip(generateTone(200, 300, 0.5));
            clipFinVague = createClip(generateMelody(new double[]{440, 550, 660}, 100, 0.5));
            clipVictoire = createClip(generateMelody(new double[]{523, 659, 784}, 170, 0.5));
            clipDefaite = createClip(generateSweep(300, 150, 600, 0.5));
            clipJingle = createClip(generateMelody(new double[]{523, 659, 784, 1047}, 150, 0.5));
            initialized = true;
        } catch (Exception e) {
            System.err.println("SoundManager: impossible d'initialiser les sons - " + e.getMessage());
        }
    }

    public static void playAchat() {
        play(clipAchat);
    }

    public static void playKill() {
        play(clipKill);
    }

    public static void playPerdVie() {
        play(clipPerdVie);
    }

    public static void playFinVague() {
        play(clipFinVague);
    }

    public static void playVictoire() {
        play(clipVictoire);
    }

    public static void playDefaite() {
        play(clipDefaite);
    }

    public static void playJingle() {
        play(clipJingle);
    }

    private static void play(AudioClip clip) {
        if (clip != null) {
            clip.play();
        }
    }

    private static byte[] generateTone(double freq, int durationMs, double volume) {
        int numSamples = (int) (SAMPLE_RATE * durationMs / 1000.0);
        byte[] data = new byte[numSamples * 2];
        for (int i = 0; i < numSamples; i++) {
            double t = (double) i / SAMPLE_RATE;
            double envelope = envelope(i, numSamples);
            short sample = (short) (Math.sin(2 * Math.PI * freq * t) * Short.MAX_VALUE * volume * envelope);
            data[i * 2] = (byte) (sample & 0xFF);
            data[i * 2 + 1] = (byte) ((sample >> 8) & 0xFF);
        }
        return data;
    }

    private static byte[] generateSweep(double freqStart, double freqEnd, int durationMs, double volume) {
        int numSamples = (int) (SAMPLE_RATE * durationMs / 1000.0);
        byte[] data = new byte[numSamples * 2];
        for (int i = 0; i < numSamples; i++) {
            double t = (double) i / SAMPLE_RATE;
            double progress = (double) i / numSamples;
            double freq = freqStart + (freqEnd - freqStart) * progress;
            double envelope = envelope(i, numSamples);
            short sample = (short) (Math.sin(2 * Math.PI * freq * t) * Short.MAX_VALUE * volume * envelope);
            data[i * 2] = (byte) (sample & 0xFF);
            data[i * 2 + 1] = (byte) ((sample >> 8) & 0xFF);
        }
        return data;
    }

    private static byte[] generateMelody(double[] freqs, int noteDurationMs, double volume) {
        int samplesPerNote = (int) (SAMPLE_RATE * noteDurationMs / 1000.0);
        int totalSamples = samplesPerNote * freqs.length;
        byte[] data = new byte[totalSamples * 2];
        for (int n = 0; n < freqs.length; n++) {
            for (int i = 0; i < samplesPerNote; i++) {
                double t = (double) i / SAMPLE_RATE;
                double envelope = envelope(i, samplesPerNote);
                short sample = (short) (Math.sin(2 * Math.PI * freqs[n] * t) * Short.MAX_VALUE * volume * envelope);
                int idx = (n * samplesPerNote + i) * 2;
                data[idx] = (byte) (sample & 0xFF);
                data[idx + 1] = (byte) ((sample >> 8) & 0xFF);
            }
        }
        return data;
    }

    private static double envelope(int sample, int totalSamples) {
        double attackRatio = 0.05;
        double releaseRatio = 0.2;
        int attackSamples = (int) (totalSamples * attackRatio);
        int releaseSamples = (int) (totalSamples * releaseRatio);
        if (sample < attackSamples) {
            return (double) sample / attackSamples;
        } else if (sample > totalSamples - releaseSamples) {
            return (double) (totalSamples - sample) / releaseSamples;
        }
        return 1.0;
    }

    private static AudioClip createClip(byte[] pcmData) throws IOException {
        AudioFormat format = new AudioFormat(SAMPLE_RATE, 16, 1, true, false);
        ByteArrayInputStream bais = new ByteArrayInputStream(pcmData);
        AudioInputStream ais = new AudioInputStream(bais, format, pcmData.length / 2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, baos);
        } catch (IOException e) {
            throw new IOException("Erreur lors de la génération du WAV", e);
        }

        Path tempFile = Files.createTempFile("pokemon_td_sfx_", ".wav");
        Files.write(tempFile, baos.toByteArray());
        tempFile.toFile().deleteOnExit();

        return new AudioClip(tempFile.toUri().toString());
    }
}

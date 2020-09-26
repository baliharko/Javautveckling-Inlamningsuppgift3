package Del_02;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-15
 * 16:06
 */

public class Player {

    private String name;
    private String datePlayed;
    private int score;

    /**
     * Konstruktor för Player-klassen. Anropar metoden setTimePlayed för att få en tidpunkt av spelet sparad i en
     * instansvariabel (datePlayed).
     * @param name en sträng med ett namn.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        setTimePlayed(); // Stämplar tidpunkten när spelaren skapades(spelet började).
    }

    /**
     * Konstruktor (privat) som endast kallas från readPlayerFromFile-metoden vid inläsning av scoreboard-fil.
     * @param name Sträng med namn
     * @param score Int med poäng
     * @param datePlayed Sträng med datum och tid för när spelaren skapades
     */
    private Player(String name, int score, String datePlayed) {
        this.name = name;
        this.score = score;
        this.datePlayed = datePlayed;
    };

    /**
     * Getter-metod för att få spelarens poäng.
     * @return Int med spelarens poäng
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter-metod för att addera poäng till spelaren.
     * @param point Int
     */
    public void addToScore(int point) {
        this.score += point;
    }

    /**
     * Getter-metod som returnerar namnet på spelaren
     * @return sträng med spelarens namn
     */
    public String getName() {
        return this.name;
    }

    /**
     * Instansmetod som sätter instansvariabeln datePlayed till den tidpunkt då metoden kallades.
     * (Kallas i konstruktorn för Player)
     */
    public void setTimePlayed() {
        datePlayed = Calendar.getInstance().getTime().toString();
        String date = datePlayed.substring(datePlayed.indexOf(' ') + 1, datePlayed.indexOf(':') - 2) +
                datePlayed.substring(datePlayed.length() - 4);
        String time = datePlayed.substring(datePlayed.indexOf(':') - 2, datePlayed.lastIndexOf(':') + 3);
        this.datePlayed = date + " - " + time;
    }

    /**
     * Getter-metod som returnerar tidpunkten då spelaren började spela.
     * @return sträng med datum och tid då metoden anropades.
     */
    public String getDatePlayed() {
        return this.datePlayed;
    }

    /**
     * Klassmetod som läser in en sträng och konverterar den till ett Player-objekt. Anropas i
     * fillScoreBoardList-metoden i Menu-klassen.
     * @param line en sträng (en rad inläst från scoreboard.txt-filen)
     * @return
     */
    public static Player readPlayerFromFile(String line) {
        line = line.trim();
        Scanner scanner = new Scanner(line);

        String datePlayed = line.substring(line.length()-24).trim();
        String name = "";
        int score = - 1;

        while (scanner.hasNext()) {
            name = scanner.next();
            while (!scanner.hasNextInt())
                name += " " + scanner.next();
            try {
                score = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Hittade ingen poäng...");
            }
            if (score > -1)
                break;
        }
        return new Player(name, score, datePlayed);
    }

    /**
     * Override på toString-metoden för att få ut en sträng i passande format till scoreboard.txt filen.
     * @return en sträng i rätt format för scoreboard.txt-filen.
     */
    @Override
    public String toString() {
        return String.format("%-20s %-15s %-10s", this.getName(), this.getScore(), this.getDatePlayed());
    }
}

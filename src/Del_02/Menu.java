package Del_02;

import java.io.*;
import java.util.*;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-15
 * 20:45
 */


public class Menu {

    private static final String MENU_HEADER = "* * * * * * * * * * * * * * * * * * * * * * * *\n" +
                                              "*                  - MENY -                   *\n" +
                                              "* * * * * * * * * * * * * * * * * * * * * * * *\n";

    private static final String MENU_START = "Ange 1, 2, 3 eller 4 för att: \n\n1.	Spela!\n2.	Visa Scoreboard\n3.  Se beskrivning\n4.	Avsluta\n";
    private static final String MENU_EXIT_PROMPT = "Är du säker på att du vill avsluta?\nAnge 1 eller 2:\n\n1.\tJa\n2.\tNej";
    private static final String SCOREBOARD_HEADER = String.format("* * * * * * * * * * *  SCOREBOARD * * * * * * * * * * * * *\n" +
                                                                   "%-20s %-20s %-10s", "Spelare:", "Poäng:", "Datum:\n" +
                                                                  "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");

    private static final String GAME_DESCRIPTION = "************************ GISSA ORDET-SPELET *************************\n" +
                                                   "*               Gissa vilket ord som visas i terminalen:            *\n" +
                                                   "*                                                                   *\n" +
                                                   "*   - Varje ord har fått bokstäverna omkastade.                     *\n" +
                                                   "*   - 10 frågor.                                                    *\n" +
                                                   "*   - Ord med 3 eller färre bokstäver ger 1 poäng.                  *\n" +
                                                   "*   - Ord med minst 4 bokstäver och under 6 bokstäver ger 2 poäng.  *\n" +
                                                   "*   - Ord med 6 eller fler bokstäver ger 3 poäng.                   *\n" +
                                                   "*                                                                   *\n" +
                                                   "*                             LYCKA TILL!                           *\n" +
                                                   "*                               //Bali                              *\n" +
                                                   "*********************************************************************";

    public static final String GO_BACK_PROMPT = "\nTryck på [Enter] för att gå tillbaka till huvudmenyn.";
    private static final String SCOREBOARD_DIRECTORY = "./src/Del_02/scoreboard.txt"; // Sökväg i projketet till scoreboard.txt
    private static final boolean TWO_PROMPT = true;
    private static final boolean MENU_PROMPT = false;
    private static final String NUMBERS = "0123456789";

    private static List<Player> scoreBoard = new ArrayList<>();

    /**
     * Klassmetod för att addera en spelad runda till scoreboarden.
     * @param player objekt av typen Player
     */
    public static void addToScoreBoard(Player player) {
        if (!new File(SCOREBOARD_DIRECTORY).exists()) {
            createScoreBoardFile();
        }
        scoreBoard.add(player);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(SCOREBOARD_DIRECTORY, true)))) {
            String add = player.toString();
            writer.println(add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Klassmetod som visar scoreboarden i terminalen.
     * @return en sträng med scoreboard-tabellen
     */
    public static String showScoreBoard() {
        scoreBoard.clear();
        fillScoreBoardList();

        String out = "";
        for (var player : scoreBoard) {
            out += player.toString() + '\n';
        }
        if (scoreBoard.isEmpty())
            return "Inga poäng sparade ännu...\n\n";
        else
            return out;
    }

    /**
     * Klassmetod som visar och navigerar i huvudmenyn i terminalen. Anropar de andra metoderna
     * i programmet beroende på vad man väljer i menyn. Denna metod anropas i main-metoden för att starta spelet.
     */
    public static void showMenu() {
        Scanner input = new Scanner(System.in);
        clearTerminal();

        System.out.println(GAME_DESCRIPTION + "\n\nTryck på [Enter] för att gå vidare. (Är du inte längst ner i terminalen får du trycka två gånger)\n");
        input.nextLine();

        while (true) {
            clearTerminal();
            System.out.println(MENU_HEADER + MENU_START);

            switch (menuSelect(MENU_PROMPT)) {
                case 1 -> {
                    clearTerminal();
                    System.out.println(MENU_HEADER + "\nAnge ditt namn:");

                    Player player = new Player(nameNoNumber());
                    System.out.println("Välkommen " + player.getName() + "! Är du redo att börja? Ange 1 eller 2:\n\n1. Ja\n2. Nej\n");
                    int prompt = menuSelect(TWO_PROMPT);
                    if (prompt == 1) {
                        clearTerminal();
                        Game game = new Game(player);
                        game.startGame();
                    }
                    clearTerminal();
                }
                case 2 -> {
                    clearTerminal();
                    if (!new File(SCOREBOARD_DIRECTORY).exists()) {
                        createScoreBoardFile();
                    }
                    System.out.println(SCOREBOARD_HEADER + showScoreBoard() + GO_BACK_PROMPT);
                    input.nextLine();
                    clearTerminal();
                }
                case 3 -> {
                    clearTerminal();
                    System.out.println(GAME_DESCRIPTION + GO_BACK_PROMPT);
                    input.nextLine();
                    clearTerminal();
                }
                case 4 -> {
                    clearTerminal();
                    System.out.println(MENU_HEADER + MENU_EXIT_PROMPT);
                    int exit = menuSelect(TWO_PROMPT);
                    clearTerminal();
                    if (exit == 1) {
                        System.out.println("\nHejdå!\n");
                        System.exit(0);
                    }
                }
            }
        }
    }

    /**
     * Klassmetod som låter användaren mata in en sträng och kontrollerar att den inte innehåller en siffra.
     * @return en sträng utan siffror.
     */
    private static String nameNoNumber() {
        Scanner input2 = new Scanner(System.in);
        String inputName;

        // Kollar att namnet inte innehåller siffror
        while (true) {
            if (input2.hasNextLine()) {
                inputName = input2.nextLine();
                boolean nameIsOkay = true;
                for (int i = 0; i < inputName.length(); i++) {
                    if (NUMBERS.indexOf(inputName.charAt(i)) >= 0) {
                        nameIsOkay = false;
                        break;
                    }
                }
                if (nameIsOkay && !(inputName.isBlank() || inputName.isEmpty()))
                    break;
                System.out.println("\nFel - Ange endast bokstäver...\nAnge ditt namn:");
            }
        }
        return inputName;
    }

    /**
     * Klassmetod för att plocka ut ett giltigt värde när man navigerar i menyerna.
     * @param isTwoPrompt en boolean som indikerar om prompten tar han om 2 alternativ eller fler.
     * @return en int med ett giltigt värde för menyn (antingen 1-4 eller 1 och 2).
     */
    private static int menuSelect(boolean isTwoPrompt) {
        int options = isTwoPrompt ? 2 : 4;

        Scanner input = new Scanner(System.in);
        String in; // Gjorde sträng som jag parseade eftersom det blev problem med nextInt-metoden i Scanner då den inte flushade sin buffer.
        int selected;
        while (true) {
            in = input.nextLine();
            try {
                selected = Integer.parseInt(in);
                if (selected <= options && selected > 0)
                    break;
                else {
                    System.out.println("Felaktig inmatning (felaktigt värde), ange en siffra som matchar menyns alternativ");
                }
            } catch (Exception e) {
                System.out.println("Felaktig inmatning (ej siffra), ange en siffra som matchar menyns alternativ");
            }
        }
        return selected;
    }

    /**<
     * Klassmetod som visar resultatet av en runda och frågar om man vill addera sitt resultat till scoreboarden.
     * Tar hjälp av addToScoreBoard-metoden i Menu-klassen.
     * @param player1 ett Player objekt
     * @param facit en array med objekt av typen Question
     */
    public static void showAfterGameMenu(Player player1, Question[] facit) {
        Scanner input = new Scanner(System.in);

        Menu.clearTerminal();
        System.out.println("* * * * * * * * * FACIT * * * * * * * * * *");
        System.out.format("%-15s %-15s %-10s\n* * * * * * * * * * * * * * * * * * * * * *\n", "Fråga nr: ", "Ordet var: ", "Svarade: ");
        for (Question q : facit) {
            String correct = q.wasCorrect() ? "korrekt!" : "Ej korrekt";
            System.out.format("% -15d %-15s %-10s\n", q.getQuestionNumber(), q.getOriginalWord(), correct);
        }

        System.out.println("\nTryck på [Enter] för att gå till nästa sida.");
        input.nextLine();
        Menu.clearTerminal();

        System.out.println("* * * * * * * * * POÄNG * * * * * * * * * *");

        System.out.println("Din poäng för denna runda blev: " + player1.getScore() + " poäng\n\n");
        System.out.println("Vill du spara din poäng till scoreboarden?\n1. Ja\n2. Nej\n\n");

        int prompt = menuSelect(Menu.TWO_PROMPT);
        if (prompt == 1) {
            addToScoreBoard(player1);
            System.out.println("Tillagd i Scoreboarden!\n");
            System.out.println("\nTryck på [Enter] för att gå till menyn.");
            input.nextLine();
        }
    }

    /**
     * Klassmetod som rensar terminalen genom att hoppa ner 80 rader.
     */
    public static void clearTerminal() {
        for (int i = 0; i < 80; i++) {
            System.out.println();
        }
    }

    /**
     * Klassmetod som kallas om ingen scoreboard-fil hittas i projektmappen. Skapar då en ny scoreboard.txt fil.
     */
    private static void createScoreBoardFile() {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(SCOREBOARD_DIRECTORY))) {
            System.out.println("Ingen fil hittades. Scoreboard-fil skapad (.txt)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Klassmetod som läser in scoreboarden från filen scoreboard.txt och fyller scoreBoard-listan i klassen.
     * Tar hjälp av readPlayerFromFile-metoden i Player-klassen för att konvertera texten till Player-variabler.
     */
    public static void fillScoreBoardList() {
        if (!new File(SCOREBOARD_DIRECTORY).exists())
            createScoreBoardFile();

        try (Scanner scan = new Scanner(new FileReader(SCOREBOARD_DIRECTORY))) {
            while (scan.hasNextLine()) {
                Player p = Player.readPlayerFromFile(scan.nextLine());

                int i;
                // Sorterar poängen med for-loop utan kropp
                for (i = 0; i < scoreBoard.size() && p.getScore() < scoreBoard.get(i).getScore(); i++)
                    ;

                scoreBoard.add(i, p);
            }
        } catch (Exception e) {
            clearTerminal();
            System.out.println("fillScoreBoardList - error");
            System.out.println(e.getMessage());
        }
    }
}


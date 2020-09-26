package Del_02;

import java.util.Scanner;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-15
 * 16:45
 */

public class Game {

    private Player player1;
    private static final String QUESTION_TEXT = "* * * * * * * * * * ORD NR: %d * * * * * * * * * * ";

    /**
     * Konstruktor för Game-klassen. Genererar en lista med ord i från klassen Word.
     * @param player1 objekt av typen Player.
     */
    public Game(Player player1) {
        this.player1 = player1;
        Word.generateWordList();
    }

    /**
     * Instansmetod som startar spelet och kallar metoden showAfterGameMenu från Menu-klassen efter en runda (10 frågor).
     */
    public void startGame() {
        Scanner input = new Scanner(System.in);
        int questionNum = 1;
        String lastQuestion = "";
        Question[] facit = new Question[10];

        Menu.clearTerminal();
        System.out.println("\n * * * * * Spelet har börjat! * * * * * \n");

        while (questionNum <= 10) {

            boolean wasCorrect = false;

            if (questionNum > 1) {
                Menu.clearTerminal();
                System.out.println(lastQuestion);
                System.out.println("Poäng: " + player1.getScore());
            }

            System.out.println(String.format(QUESTION_TEXT, questionNum));
            System.out.println("Vilket ord är detta? (tryck [Enter] utan att ange något för \"pass\")\n");
            String word = Word.getRandomWord();
            System.out.println(Word.generateAnagram(word) + '\n');

            if (input.nextLine().equalsIgnoreCase(word)) {
                lastQuestion = " \n* * * * * * * * * * * RÄTT!!! * * * * * * * * * * ";
                wasCorrect = true;

                int point = word.length() <= 3 ? 1 :
                        word.length() >= 6 ? 3 : 2;

                this.player1.addToScore(point);
            } else {
                lastQuestion = "\n* * * * * * * * * * * FEL...  * * * * * * * * * * ";
            }
            facit[questionNum - 1] = new Question(word, wasCorrect);
            questionNum++;
        }

        Menu.showAfterGameMenu(player1, facit);
    }
}

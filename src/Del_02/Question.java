package Del_02;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-16
 * 19:43
 */

public class Question {
    private static int questionsAsked = 1; // Statisk int som ger varje objekt ett unikt id.

    private String originalWord;
    private boolean wasCorrect;
    private int questionNumber;

    /**
     * Kontruktor för Question-klassen. Sparar en sträng med orginal-ordet och en boolean som är true
     * om användaren svarade rätt, annars false. Ger även varje fråga ett eget nummer.
     * @param originalWord sträng med ett ord.
     * @param wasCorrect boolean som är true om användaren svarade rätt, annars false.
     */
    public Question(String originalWord, boolean wasCorrect) {
        this.originalWord = originalWord;
        this.wasCorrect = wasCorrect;
        this.questionNumber = questionsAsked;
        questionsAsked++;
    }

    /**
     * Getter-metod för att få ut orginal-ordet.
     * @return en sträng med orginal-ordet
     */
    public String getOriginalWord() {
        return originalWord;
    }

    /**
     * Getter-metod för att få ut om svaret man gav på frågan var korrekt.
     * @return true om man svarade rätt, annars false
     */
    public boolean wasCorrect() {
        return wasCorrect;
    }

    /**
     * Getter-metod för att få vilket id nummer frågan har.
     * @return en int med objektets nummer
     */
    public int getQuestionNumber() {
        return questionNumber;
    }
}

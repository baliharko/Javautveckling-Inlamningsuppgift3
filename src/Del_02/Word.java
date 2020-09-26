package Del_02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-15
 * 17:08
 */

public class Word {

    private static List<String> wordList; // Ordlista

    /**
     * Klassmetod som läser in en lista med ord från en textfil i projektmappen och sparar varje ord i
     * klassvariabeln wordList (ArrayList).
     */
    public static void generateWordList() {
        wordList = new ArrayList<>();
        try (Scanner inFile = new Scanner(new File("./src/Del_02/words.txt"))) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                wordList.add(s);
            }
        } catch (IOException e) {
            System.out.println("Hittade ingen fil.");
            e.printStackTrace();
        }
    }

    /**
     * Klassmetod som plockar fram ett slumpvalt ord ur ordlistan.
     * @return en sträng med ett ord.
     */
    public static String getRandomWord() {
        Random rand = new Random();
        int randIndex = rand.nextInt(wordList.size() - 1);
        return wordList.get(randIndex);
    }

    /**
     * Klassmetod som kastar om bokstäverna i strängen den tar emot.
     * @param s en sträng
     * @return en sträng innehållande samma bokstäver som emottagen sträng, men omkastade.
     */
    public static String generateAnagram(String s) {
        s = s.trim().toLowerCase();
        String out = "";
        Random rand = new Random();

        List<Character> charList = new ArrayList<>(); //Spara alla bokstäver i en lista
        for (int i = 0; i < s.length(); i++)
            charList.add(s.charAt(i));

        //Kolla om bokstaven vid indexet i strängen är använt
        boolean[] addedIndexes = new boolean[s.length() - 1];

        while (!charList.isEmpty()) {
            int randChar = rand.nextInt(s.length() - 1);
            if (!addedIndexes[randChar] && randChar < charList.size()) {
                out += charList.remove(randChar);
            }
        }
        return out;
    }
}

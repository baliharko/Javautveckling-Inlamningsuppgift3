package Del_01;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-14
 * 19:32
 */

public class Elevator {

    private int level;
    private static int elevatorNumber = 1; // Statisk variabel för att tilldela unika nummer till instansvariablerna(Hissarna)
    public int instanceNum;

    /**
     * Konstruktor för Elevator. Sätter våning med hjälp av setLevel-metoden och tilldelar objektet ett unikt nummer med
     * hjälp av assignElevatorNumber-metoden.
     * @param level int med värde för önskad våning
     */
    public Elevator(int level) {
        this.setLevel(level);
        assignElevatorNumber();
    }

    /**
     * Instansmetod som tilldelar varje objekt ett unikt id-nummer från 1.
     */
    private void assignElevatorNumber() {
        this.instanceNum = elevatorNumber;
        elevatorNumber++;
    }

    /**
     * Setter-metod för objektets våning.
     * @param level int med värde för önskad våning
     */
    private void setLevel(int level) {
        if (isLevel(level))
            this.level = level;
        else
            throw new IllegalArgumentException("Våning " + level + " finns inte");
    }

    /**
     * Instansmetod för att kontrollera att en våning är giltig
     * @param level int önskad våning
     * @return true om våningen är giltig (från -2 till 10), annars false.
     */
    private boolean isLevel(int level) {
        return level >= -2 && level <= 10;
    }

    /**
     * Instansmetod for att byta våning.
     * @param level int med önskad våning
     */
    public void goTo(int level) {
        String text = level > where() ? "Hiss nummer " + this.instanceNum + " åker upp till våning " :
                level < where() ? "Hiss nummer " + this.instanceNum + " åker ner till våning " : "Hiss nummer " + this.instanceNum + " står redan på våning ";

        this.setLevel(level);
        System.out.println(text + level);
    }

    /**
     * Getter-metod för att ta reda på hissens nuvarande våning.
     * @return int med värdet för hissens våning.
     */
    public int where() {
        return this.level;
    }
}

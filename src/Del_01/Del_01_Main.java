package Del_01;

/**
 * Inlamningsuppgift3
 * baliharko
 * 2020-09-15
 * 08:36
 */

public class Del_01_Main {
    public static void main(String[] args) {
        Elevator e1 = new Elevator(0);
        Elevator e2 = new Elevator(-2);
        Elevator e3 = new Elevator(4);

        System.out.println("Hiss nummer " + e1.instanceNum + " är på våning " + e1.where());
        System.out.println("Hiss nummer " + e2.instanceNum + " är på våning " + e2.where());

        System.out.println();

        e1.goTo(3);
        e2.goTo(0);
        e3.goTo(1);
        e3.goTo(1);
        //e3.goTo(-6);  // Fel

        System.out.println("Hiss nummer " + e3.instanceNum + " är på våning " + e3.where());
        System.out.println("Hiss nummer " + e1.instanceNum + " är på våning " + e1.where());

        //var e4 = new Elevator(13); // Fel
    }
}

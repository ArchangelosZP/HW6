import javax.script.ScriptException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calc extends ElectricalAappliance implements Switcheble {
    String mode;

    static Scanner scanner = new Scanner(System.in);

    // Again, I wrote the code incorrectly - I wrote it as I would do if it were a real task. Now all the same, he began to plan a relocation abroad. Not enough time to do quality homework.
    public void calcM() {
        System.out.println("Введите выражение которое надо вычислить или же введите exit для перехода к второму заданию");
        String text = scanner.nextLine();
        if ("exit".equals(text)) {
            return;
        }
        Expressions object = new Expressions();
        try {
            System.out.println(object.make(text));
        } catch (IndexOutOfBoundsException | NoSuchElementException | NumberFormatException e) {
            calcM();
        }
        calcM();
    }

    public String getMode() {
        return this.mode;
    }

    @Override // we will assume that the calculator is charging faster than other devices
    public void addCharge(){
        this.chargeLevel = this.chargeLevel+5;
    }

    @Override
    public void turnOff() {
        this.switchedOn = false;
    }

    @Override
    public void turnOn() {
        this.switchedOn = true;
    }

    @Override
    public void SwitchToAdvancedMode() {
        this.mode = "Advanced";
    }

    @Override
    public void SwitchToSimpleMode() {
        this.mode = "Simple";
    }

}



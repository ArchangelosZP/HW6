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

    private class Expressions {


        public boolean isOperation(char c) {
            return c == '+' || c == '-' || c == '/' || c == '%' || c == '*';
        }

        public boolean interval(char c) {
            return c == ' ';
        }

        public int opearatorsPriority(char operand) {
            switch (operand) {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                case '%':
                    return 2;
                default:
                    return -1;
            }
        }

        public void operator(LinkedList<Integer> cislo, char znak) {
            int r = cislo.removeLast();
            int l = cislo.removeLast();
            switch (znak) {
                case '+':
                    cislo.add(l + r);
                    break;
                case '-':
                    cislo.add(l - r);
                    break;
                case '*':
                    cislo.add(l * r);
                    break;
                case '/':
                    cislo.add(l / r);
                    break;
                case '%':
                    cislo.add(l % r);
                    break;
            }
        }


        public int make(String s) {
            Expressions obj = new Expressions();
            LinkedList<Integer> h = new LinkedList<>();
            LinkedList<Character> op = new LinkedList<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (obj.interval(c))
                    continue;

                if (c == '(') {
                    op.add('(');
                } else if (c == ')') {
                    while (op.getLast() != '(')
                        operator(h, op.removeLast());
                    op.removeLast();
                } else if (obj.isOperation(c)) {
                    while (!op.isEmpty() && obj.opearatorsPriority(op.getLast()) >= obj.opearatorsPriority(c))
                        operator(h, op.removeLast());
                    op.add(c);
                } else {
                    StringBuilder operand = new StringBuilder();
                    while (i < s.length() && Character.isDigit(s.charAt(i)))
                        operand.append(s.charAt(i++));
                    --i;
                    h.add(Integer.parseInt(operand.toString()));
                }
            }

            while (!op.isEmpty())
                operator(h, op.removeLast());
            return h.get(0);

        }

    }

}



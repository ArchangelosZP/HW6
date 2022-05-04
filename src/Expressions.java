import java.util.LinkedList;

public class Expressions {

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
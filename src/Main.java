import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean minus;

    public static void main(String[] args) {
        String str = "";
        System.out.println("Enter your expression: ");

        //read from keyboard and write to String
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            str = reader.readLine();
        } catch (IOException e) {
            System.out.println("Can't read your expression");;
        }
        try {
            recursion(str);
        } catch (Exception e) {
            System.out.println("You entered wrong symbol");
        }
    }

    private static String recursion(String str) throws Exception {

        int firstElement = getElement(str, 0);
        int operandPosition = findOperand(str);
        int secondElement = getElement(str, operandPosition + 1);

        String operand = getOperand(operandPosition, str);
        int result = getResult(firstElement, secondElement, operand);
        String res = Integer.toString(result);

        if(minus) {
            firstElement = - + firstElement;
        }
        str = str.replace(firstElement + operand + secondElement, res);
        minus = false;

        if ((findOperand(str)) == -1) {
            System.out.println("Result is: " + result);
            System.exit(0);
        }
        return recursion(str);
    }

    private static int getResult(int firstElement, int secondElement, String operand) {
        int sum;
        if (minus) {
            if (operand.equals("+")) {
                sum = - + firstElement + secondElement;
            } else {
                sum = - + firstElement - secondElement;
            }
            return sum;
        } else {
            if (operand.equals("+")) {
                sum = firstElement + secondElement;
            } else {
                sum = firstElement - secondElement;
            }
        }
        return sum;
    }

    private static String getOperand(int operandPosition, String str) {
        Character s = str.charAt(operandPosition);
        String op = s.toString();
        if ("+".equals(op)) {
            op = "+";
        } else {
            op = "-";
        }
        return op;
    }

    private static int getElement(String str, int startPosition){

            Character s = str.charAt(startPosition);

            if ((startPosition == 0) && (s == '-') ) {
                minus = true;
                startPosition++;
                s = str.charAt(startPosition);
            }

            String rslt = s.toString();
            while (! (s == '+') || (s == '-')) {
                if ((startPosition + 1) == (str.length())) {
                    return Integer.parseInt(rslt);
                }
                s = str.charAt(++startPosition);
                String st = s.toString();
                    if ((s == '+') || (s == '-'))  {
                        return Integer.parseInt(rslt);
                    }else {
                        rslt += st;
                    }
            }
        return Integer.parseInt(rslt);
    }

    public static int findOperand(String str) {

        for (int i = 1; i < str.length(); i++) {
            Character s = str.charAt(i);
            String st = s.toString();
            if (st.equals("+") || st.equals("-")) {
                return i;
            }
        }
        return -1;
    }
}




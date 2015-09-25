import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean minus;
    static boolean firstZero = false;
    static boolean secondZero = false;

    public static void main(String[] args) {
        String str = "";
        System.out.println("Enter your expression: ");

        //read from keyboard and write to String
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            str = reader.readLine();
        } catch (IOException e) {
            System.out.println("Can't read your expression");
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

        if(firstZero && secondZero){
            str = str.replace(0 + firstElement + operand + 0 + secondElement, res);
        }else {
            if (firstZero) {
                str = str.replace(0 + firstElement + operand + secondElement, res);
            }else {
                if (secondZero) {
                    str = str.replace(firstElement + operand + 0 + secondElement, res);
                } else {
                    str = str.replace(firstElement + operand + secondElement, res);
                }
            }
        }
        minus = false;
        firstZero = false;
        secondZero = false;


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

    private static int getElement(String str, int startPosition)  throws Exception {

            Character s = str.charAt(startPosition);

            if ((startPosition == 0) && (s == '-') ) {
                minus = true;
                startPosition++;
                s = str.charAt(startPosition);
                if (s == '0') {
                    throw new Exception();
                }
            }

            String rslt = s.toString();
            while (! (s == '+') || (s == '-')) {
                if ((startPosition + 1) == (str.length())) {
                    return Integer.parseInt(rslt);
                }
                s = str.charAt(++startPosition);
                String st = s.toString();

                if (rslt.equals("0")) {
                    if(firstZero) {
                        secondZero = true;
                    }
                    firstZero = true;
                    if ((s == '+') || (s == '-')) {
                        return Integer.parseInt(rslt);
                    } else {
                        return Integer.parseInt(st);
                    }
                }
                if ((s == '+') || (s == '-')) {
                    return Integer.parseInt(rslt);
                } else {
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




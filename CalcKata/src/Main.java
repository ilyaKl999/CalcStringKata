import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Unchecked_User_Exception {
        Scanner scanner = new Scanner(System.in);
        String user_Expression = scanner.nextLine();
        char a = '"';
        calculator(user_Expression);
        String result = a + calculator(user_Expression) + a;
        System.out.println(result);
    }

    public static String calculator(String user_Expression) throws Unchecked_User_Exception {
        String result = null;
        if (user_Expression.charAt(0) != '"') {
            throw new Unchecked_User_Exception("Первая переменная всегда строка которая начинается с ковычек");
        }
        int a = 0;
        for (int i = 0; i < user_Expression.length(); i++) {
            if (user_Expression.charAt(i) == '"') {
                a++;
            }
        }
        if (a > 4 | a < 2 | a == 3) {
            throw new Unchecked_User_Exception("ковычки являются отделителем переменных и действия, по условию их может быть 2 или 4(одна или две строковые переменные соответственно)");
        }
        String[] array_User_Ex = user_Expression.split("\"");
        String variable1 = array_User_Ex[1];
        if (variable1.length() > 10) {
            throw new Unchecked_User_Exception("Строка может содержать не более 10 символов");
        }
        if (a == 2) {
            String[] array_Variable2 = array_User_Ex[2].split(" ");
            String operation = array_Variable2[1];
            try {
                int variable2 = Integer.parseInt(array_Variable2[2]);
                if (variable2 > 10 | variable2 < 1) {
                    throw new Unchecked_User_Exception("положительные+целочисленные от 1 до 10");
                }
                switch (operation) {
                    case "*":
                        result = Operation.multiplication(variable1, variable2);
                        return result;
                    case "/":
                        result = Operation.division(variable1, variable2);
                        return result;
                    default:
                        throw new Unchecked_User_Exception("Ежели вторая переменная целочисленная то знаки доступные для операции только * или /");
                }
            } catch (NumberFormatException string_Expression) {
                throw new Unchecked_User_Exception("Вторая переменная может быть либо целочисленным положительным числом от 1 до 10 либо строчкой до 10 символов которая ограничена ковычками");
            }
        } else {
            String variable2 = array_User_Ex[3];
            if (variable2.length() > 10) {
                throw new Unchecked_User_Exception("Вторая, как и первая, переменная может содержать не более 10 символов");
            }
            char operation = array_User_Ex[2].charAt(1);
            switch (operation) {
                case '+':
                    result = Operation.addition(variable1, variable2);
                    return result;

                case '-':
                    result = Operation.subtraction(variable1, variable2);
                    return result;

                default:
                    throw new Unchecked_User_Exception("шаблон: первая переменная в ковычках, пробел, знак действия,пробел, вторая переменная в кавычках, а ежели вторая переменная в виде строки то знаки только - и +");
            }
        }
    }
}

class Operation {
    public static String multiplication(String a, int b) {
        String result = a;
        for (int i = 0; i < b - 1; i++) {
            result = result + a;
        }
        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
            return result;
        }
        return result;
    }

    public static String division(String a, int b) throws Unchecked_User_Exception {
        if (a.length() < b) {
            throw new Unchecked_User_Exception("Колличество символов в строке меньше второй переменной. нелогично");
        }
        String result = a;
        int d = a.length();
        int div = d / b;
        result = a.substring(0, div);
        return result;
    }

    public static String addition(String a, String b) {
        String result = a + b;
        return result;
    }

    public static String subtraction(String a, String b) {
        boolean search = a.contains(b);
        if (search == true) {
            String empty = "";
            String cut = b;
            String result = a.replace(cut, empty);
            return result;
        } else {
            String result = a;
            return result;
        }

    }
}

class Unchecked_User_Exception extends Exception {
    public Unchecked_User_Exception(String message) {
        super(message);
    }
}
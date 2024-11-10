package org.example;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Variables variables = new Variables();
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        System.out.print("Введите количество переменных: ");
        int n = in.nextInt();
        in.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Введите название " + (i + 1) + " переменной (одна строчная буква от 'a' до 'z'): ");
            String name = in.nextLine();
            System.out.print("Введите значение переменной "  + name + ": ");
            double value = in.nextDouble();
            in.nextLine();

            variables.setVariable(name, value);
        }
        System.out.print("Введите выражение для вычисления, используя круглые скобки: ");
        String expression = in.nextLine();

        Calculator calculator = new Calculator(variables);
        double result = calculator.evaluate(expression);

        if (!Double.isNaN(result)) {
            System.out.print("Результат выражения: "  + result);
        }
        else {
            System.out.print("Ошибка при вычислении выражения");
        }
    }
}
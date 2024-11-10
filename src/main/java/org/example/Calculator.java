package org.example;

/**
 * Класс, реализующий выполнение математических операций над числами и переменными
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Variables
 * @see Stack
 */
public class Calculator {
    /**
     * Объект класса Variables, используемый для хранения значений переменных
     */
    private Variables variables;

    /**
     * Конструктор, инициализирующий новые переменные
     */
    public Calculator() {
        this.variables = new Variables();
    }

    /**
     * Конструктор, принимающий существующие переменные
     * @param variables заданные переменные
     */
    public Calculator(Variables variables) {
        this.variables = variables;
    }

    /**
     * Метод определения приоритета оператора
     * @param operator оператор, для которого нужно определить приоритет
     * @return приоритет оператора (целое число)
     */
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Метод обработки оператора и выполнения соответствующей математической операции, содержащей скобки
     * @param numbers стек чисел, над которыми будет выполнена операция
     * @param operator заданный оператор
     */
    private void processOperator(Stack<Double> numbers, char operator) {
        if (numbers.size() < 2) {
            System.out.println("Ошибка: недостаточно чисел для операции " + operator);
            numbers.push(Double.NaN);
            return;
        }
        double b = numbers.pop();
        double a = numbers.pop();
        switch (operator) {
            case '+':
                numbers.push(a + b);
                break;
            case '-':
                numbers.push(a - b);
                break;
            case '*':
                numbers.push(a * b);
                break;
            case '/':
                if (b != 0) {
                    numbers.push(a / b);
                }
                else {
                    System.out.println("Ошибка: деление на ноль");
                    numbers.push(Double.NaN);
                }
                break;
        }
    }

    /**
     * Метод вычисления математического выражения
     * @param expression математическое выражение (строка)
     * @return результат вычисления выражения или NaN в случае ошибки
     */
    public double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String numberBuffer = "";
        int openBrackets = 0;

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current == ' ') {
                continue;
            }
            if (current >= '0' && current <= '9' || current == '.') {
                numberBuffer += current;
            }
            else {
                if (!numberBuffer.isEmpty()) {
                    numbers.push(Double.parseDouble(numberBuffer));
                    numberBuffer = "";
                }
                if (current >= 'a' && current <= 'z') {
                    double variableValue = variables.getVariable(String.valueOf(current));
                    if (Double.isNaN(variableValue)) {
                        return Double.NaN;
                    }
                    numbers.push(variableValue);
                }
                else {
                    if (current == '+' || current == '-' || current == '*' || current == '/') {
                        while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(current)) {
                            processOperator(numbers, operators.pop());
                        }
                        operators.push(current);
                    }
                    else {
                        if (current == '(') {
                            operators.push(current);
                            openBrackets++;
                        }
                        else {
                            if (current == ')') {
                                while (!operators.isEmpty() && operators.peek() != '(') {
                                    processOperator(numbers, operators.pop());
                                }
                                if (!operators.isEmpty()) {
                                    operators.pop();
                                    openBrackets--;
                                }
                                else {
                                    System.out.println("Ошибка: недопустимая скобка");
                                    return Double.NaN;
                                }
                            }
                            else {
                                System.out.println("Ошибка: недопустимый символ " + current);
                                return Double.NaN;
                            }
                        }
                    }
                }
            }
        }
        if (!numberBuffer.isEmpty()) {
            numbers.push(Double.parseDouble(numberBuffer));
        }
        while (!operators.isEmpty()) {
            processOperator(numbers, operators.pop());
        }
        if (openBrackets > 0) {
            System.out.println("Ошибка: недопустимая скобка");
            return Double.NaN;
        }
        if (numbers.isEmpty()) {
            return Double.NaN;
        }
        double result = numbers.pop();
        return Double.isNaN(result) ? Double.NaN : round(result);
    }

    /**
     * Метод округления числа до 5 знаков после запятой
     * @param value заданное значение
     * @return округленное до 5 знаков после запятой заданное значение
     */
    private double round(double value) {
        return Math.round(value * 100000.0) / 100000.0;
    }
}

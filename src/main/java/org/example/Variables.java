package org.example;

/**
 * Класс для хранения и управления переменными, где переменные - строчные буквы от 'a' до 'z'
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Stack
 * @see Calculator
 */
public class Variables {
    /**
     * Массив, хранящий значения переменных
     */
    private double[] values;
    /**
     * Массив, указывающий, установленны ли значения переменных
     */
    private boolean[] isSet;

    /**
     * Конструктор, инициализирующий массивы значений и установленных переменных
     */
    public Variables() {
        values = new double[26];
        isSet = new boolean[26];
    }

    /**
     * Метод, устанавливающий значение переменной с заданным именем
     * @param name имя переменной, должно состоять из одной строчной буквы от 'a' до 'z'
     * @param value значение, которое нужно установить для переменной
     */
    public void setVariable(String name, double value) {
        if (name.length() == 1 && name.charAt(0) >= 'a' && name.charAt(0) <= 'z') {
            values[name.charAt(0) - 'a'] = value;
            isSet[name.charAt(0) - 'a'] = true;
        }
        else {
            System.out.println("Ошибка: имя переменной должно состоять из одной строчной буквы от 'a' до 'z'");
        }
    }

    /**
     * Метод получения значения переменной с заданными именем
     * @param name имя переменной, должно состоять из одной строчной буквы от 'a' до 'z'
     * @return значение переменной, если она установлена; NaN, если переменная не установлена или имя некорректно
     */
    public double getVariable(String name) {
        if (name.length() == 1 && name.charAt(0) >= 'a' && name.charAt(0) <= 'z') {
            if (!isSet[name.charAt(0) - 'a']) {
                System.out.println("Ошибка: переменная не установлена");
                return Double.NaN;
            }
            return values[name.charAt(0) - 'a'];
        }
        else {
            System.out.println("Ошибка: имя переменной должно состоять из одной строчной буквы от 'a' до 'z'");
            return Double.NaN;
        }
    }
}

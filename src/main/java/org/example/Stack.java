package org.example;

/**
 * Класс, описывающий стек
 * @param <T> тип элементов, которые будут храниться в стеке
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Variables
 * @see Calculator
 */
public class Stack<T> {
    /**
     * Массив, хранящий элементы стека
     */
    private Object[] stack;
    /**
     * Индекс верхнего элемента стека
     */
    private int top;
    /**
     * Вместимость стека
     */
    private int capacity;

    /**
     * Конструктор создания стека заданного размера
     * @param size заданный размер стека
     */
    public Stack(int size) {
        stack = new Object[size];
        capacity = size;
        top = -1;
    }

    /**
     * Конструктор по умолчанию создания стека размером 100
     */
    public Stack() {
        this(100);
    }

    /**
     * Метод получения количества элементов в стеке
     * @return количество элементов в стеке
     */
    public int size() {
        return top + 1;
    }

    /**
     * Метод проверки стека на наличие в нем элементов
     * @return true, если стек пуст, false в противном случае
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Метод добавления элементов в стек
     * @param value элемент, который необходимо добавить в стек
     */
    public void push(T value) {
        if (top == capacity - 1) {
            System.out.println("Ошибка: стек переполнен");
            return;
        }
        stack[++top] = value;
    }

    /**
     * Метод удаления и возврата элемента стека
     * @return элемент из вершины стека или null, если стек пуст
     */
    public T pop() {
        if (isEmpty()) {
            System.out.println("Ошибка: стек пуст");
            return null;
        }
        return (T) stack[top--];
    }

    /**
     * Метод возврата элемента стека без его удаления
     * @return элемент из вершины стека или null, если стек пуст
     */
    public T peek() {
        if (isEmpty()) {
            System.out.println("Ошибка: стек пуст");
            return null;
        }
        return (T) stack[top];
    }
}

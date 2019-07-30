package Snippets;

public class DoWhile {
    public static void main(String[] args) {
        int number = 3;// Возводимое в степень число
        int result = number;// Результат возведения в степень
        int power = 1;// Начальный показатель степени
        do {
            System.out.println(number + " в степени " + power + " = " + result);
            power++;
            result = result * number;
        } while (result < 10000); // условие выхода из цикла

    }
}

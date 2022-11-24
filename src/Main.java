import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static String[] fruit = {"Apple", "Orange"};
    static Integer[] num = {1, 2};

    public static void main(String[] args) {

        System.out.println(Arrays.toString(fruit)); //Печатаем массив fruit
        changeElement(fruit); //Меняем элементы местами в массиве fruit
        System.out.println(Arrays.toString(fruit)); //Печатаем массив fruit повторно

        System.out.println(Arrays.toString(num)); //Печатаем массив num
        changeElement(num); //Меняем элементы местами в массиве num
        System.out.println(Arrays.toString(num)); //Печатаем массив num повторно

        System.out.println(convertToArraylist(fruit).getClass().getName()); // Конвертируем массив в ArrayList, печатаем и проверяяем

        Orange orange = new Orange(1.5f); // добавляем экземпляр Orange
        Apple apple = new Apple(1.0f); // добавляем экземпляр Apple
        Box<Orange> orangeBox = new Box<>(orange, 4); // Создаем коробку с Orange 4 шт
        orangeBox.printBox(); // Печатаем количество Fruit в коробке
        orangeBox.getWeightBoxPrint(); //Печатаем вес коробки с Fruit
        orangeBox.addFruit(2); // Добавляем 2 Fruit в коробку
        orangeBox.printBox(); // Печатаем количество Fruit в коробке
        orangeBox.getWeightBoxPrint(); //Печатаем вес коробки с Fruit

        Box<Apple> appleBox = new Box<>(apple, 9); // Создаем коробку с Apple 9 шт
        appleBox.getWeightBoxPrint(); //Печатаем вес коробки с Fruit

        System.out.println();
        System.out.println(appleBox.compare(orangeBox)); // Сравниваем вес двух коробок с Fruit

        System.out.println();

        Box<Orange> orangeBox1 = new Box<>(orange, 5); // Создаем коробку с Orange 5 шт

        Transaction<Box> transaction = new Transaction<>(orangeBox1, orangeBox, 2); // создаем экземпляр транзакции
        transaction.execute(); // Выполняем транзакцию
        orangeBox.printBox(); // Печатаем количество Fruit в коробке (Проверяем выполнение транзакции)
        orangeBox1.printBox(); // Печатаем количество Fruit в коробке (Проверяем выполнение транзакции)

    }


    /**
     * Задача 1. Массив меняет два элемента местами
     *
     * @param fruit Массив
     * @param <T>   обобщенного типа
     */
    public static <T> void changeElement(T[] fruit) {
        T a = fruit[0];
        fruit[0] = fruit[1];
        fruit[1] = a;
    }

    /**
     * Задача2. конвертер массива в ArrayList
     *
     * @param fruit
     * @return
     */
    public static ArrayList convertToArraylist(String[] fruit) {
        return new ArrayList<>(Arrays.asList(fruit));
    }
}

/**
 * Задача 3f. Пересыпаем фрукты в коробку
 * @param <T>
 */
    class Transaction<T extends Box> {
        private T from;
        private T to;
        private float amount;


        public Transaction(T from, T to, float amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        public void execute() {
            if (from.getAmount() >= amount) {
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
                System.out.printf("Переложили %.0f %s\n", amount, from.getFruit().getName());
                System.out.printf("В коробке 1 осталось %.0f %s.\nВ Коробке 2 стало %.0f %s", from.getAmount(), from.getFruit().getName(), to.getAmount(), to.getFruit().getName());
                ;

            } else {
                System.out.println("переместить невозможно. В коробке не хватает фруктов.");
            }
        }
    }

/**
 * Задача 3а. Класс Fruit
 */
    interface Fruit {
        float getWeight();

        String getName();

    }

/**
 * Задача 3a. Класс Apple extends Fruit
 */
class Apple implements Fruit {

        private float weight;

        public Apple(float weight) {
            this.weight = weight;
        }

        @Override
        public float getWeight() {
            return weight;
        }

        @Override
        public String getName() {
            return "Apple";
        }
    }

/**
 * Задача 3a. Класс Apple extends Fruit
 */
    class Orange implements Fruit {
        private float weight;

        public Orange(float weight) {
            this.weight = weight;
        }

        @Override
        public float getWeight() {
            return weight;
        }

        @Override
        public String getName() {
            return "Orange";
        }
    }

/**
 * Задача 3b. Класс Box
 * @param <T>
 */
class Box<T extends Fruit> {
        private T fruit;
        private float amount;

        public T getFruit() {
            return fruit;
        }

        public float getAmount() {
            return amount;
        }

        public void setFruit(T fruit) {
            this.fruit = fruit;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

    /**
     * Задача 3c. Для хранения фруктов используем ArrayList
     */
        ArrayList<T> fr = new ArrayList<>();
        public Box(T fruit, float amount) {
            this.fruit = fruit;
            this.amount = amount;

            for (int i = 0; i < amount; i++) {
                fr.add(fruit);
            }
        }

    /**
     * Задача 3d. Считаем вес коробки
     * @return
     */

    public float getWeightBox(){
            return fruit.getWeight() * amount;

        }

    /**
     * Выводим вес коробки на экран
     */
    public void getWeightBoxPrint(){
            float a = fruit.getWeight() * amount;
            System.out.printf("\nВес коробки с %s: %.1f кг\n",fruit.getName(), a);
        }

    /**
     * Проверяем остаток фруктов в коробке
     */
    public void printBox(){

            System.out.printf("\nВ коробке: %.0f %s", amount,fruit.getName());

        }

    /**
     * Задача 3g. Добавляем фрукты в коробку
     * @param add
     */
        public void addFruit(int add){
            amount += add;
            for (int i = 0; i < add; i++) {
                fr.add(fruit);
            }
        }

    /**
     * Задача 3e. Сравниваем вес коробок
     * @param a
     * @return
     */
    public boolean compare(Box a){
            float amountBox = a.getWeightBox();
            if (amount == amountBox) return true;
          return false;
        }

    }



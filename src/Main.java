import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static String[] fruit = {"Apple", "Orange"};
    static Integer[] num = {1, 2};

    public static void main(String[] args) {

        System.out.println(Arrays.toString(fruit)); //�������� ������ fruit
        changeElement(fruit); //������ �������� ������� � ������� fruit
        System.out.println(Arrays.toString(fruit)); //�������� ������ fruit ��������

        System.out.println(Arrays.toString(num)); //�������� ������ num
        changeElement(num); //������ �������� ������� � ������� num
        System.out.println(Arrays.toString(num)); //�������� ������ num ��������

        System.out.println(convertToArraylist(fruit).getClass().getName()); // ������������ ������ � ArrayList, �������� � ����������

        Orange orange = new Orange(1.5f); // ��������� ��������� Orange
        Apple apple = new Apple(1.0f); // ��������� ��������� Apple
        Box<Orange> orangeBox = new Box<>(orange, 4); // ������� ������� � Orange 4 ��
        orangeBox.printBox(); // �������� ���������� Fruit � �������
        orangeBox.getWeightBoxPrint(); //�������� ��� ������� � Fruit
        orangeBox.addFruit(2); // ��������� 2 Fruit � �������
        orangeBox.printBox(); // �������� ���������� Fruit � �������
        orangeBox.getWeightBoxPrint(); //�������� ��� ������� � Fruit

        Box<Apple> appleBox = new Box<>(apple, 9); // ������� ������� � Apple 9 ��
        appleBox.getWeightBoxPrint(); //�������� ��� ������� � Fruit

        System.out.println();
        System.out.println(appleBox.compare(orangeBox)); // ���������� ��� ���� ������� � Fruit

        System.out.println();

        Box<Orange> orangeBox1 = new Box<>(orange, 5); // ������� ������� � Orange 5 ��

        Transaction<Box> transaction = new Transaction<>(orangeBox1, orangeBox, 2); // ������� ��������� ����������
        transaction.execute(); // ��������� ����������
        orangeBox.printBox(); // �������� ���������� Fruit � ������� (��������� ���������� ����������)
        orangeBox1.printBox(); // �������� ���������� Fruit � ������� (��������� ���������� ����������)

    }


    /**
     * ������ 1. ������ ������ ��� �������� �������
     *
     * @param fruit ������
     * @param <T>   ����������� ����
     */
    public static <T> void changeElement(T[] fruit) {
        T a = fruit[0];
        fruit[0] = fruit[1];
        fruit[1] = a;
    }

    /**
     * ������2. ��������� ������� � ArrayList
     *
     * @param fruit
     * @return
     */
    public static ArrayList convertToArraylist(String[] fruit) {
        return new ArrayList<>(Arrays.asList(fruit));
    }
}

/**
 * ������ 3f. ���������� ������ � �������
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
                System.out.printf("���������� %.0f %s\n", amount, from.getFruit().getName());
                System.out.printf("� ������� 1 �������� %.0f %s.\n� ������� 2 ����� %.0f %s", from.getAmount(), from.getFruit().getName(), to.getAmount(), to.getFruit().getName());
                ;

            } else {
                System.out.println("����������� ����������. � ������� �� ������� �������.");
            }
        }
    }

/**
 * ������ 3�. ����� Fruit
 */
    interface Fruit {
        float getWeight();

        String getName();

    }

/**
 * ������ 3a. ����� Apple extends Fruit
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
 * ������ 3a. ����� Apple extends Fruit
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
 * ������ 3b. ����� Box
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
     * ������ 3c. ��� �������� ������� ���������� ArrayList
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
     * ������ 3d. ������� ��� �������
     * @return
     */

    public float getWeightBox(){
            return fruit.getWeight() * amount;

        }

    /**
     * ������� ��� ������� �� �����
     */
    public void getWeightBoxPrint(){
            float a = fruit.getWeight() * amount;
            System.out.printf("\n��� ������� � %s: %.1f ��\n",fruit.getName(), a);
        }

    /**
     * ��������� ������� ������� � �������
     */
    public void printBox(){

            System.out.printf("\n� �������: %.0f %s", amount,fruit.getName());

        }

    /**
     * ������ 3g. ��������� ������ � �������
     * @param add
     */
        public void addFruit(int add){
            amount += add;
            for (int i = 0; i < add; i++) {
                fr.add(fruit);
            }
        }

    /**
     * ������ 3e. ���������� ��� �������
     * @param a
     * @return
     */
    public boolean compare(Box a){
            float amountBox = a.getWeightBox();
            if (amount == amountBox) return true;
          return false;
        }

    }



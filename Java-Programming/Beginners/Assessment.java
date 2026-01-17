package Beginners;

public class Assessment {
    public static void main(String[] args){
        // Implicit type casting(Widening conversion)
        int i =10;
        double d = i;
        System.out.println("Implicit type casting(Widening conversion):");
        System.out.println("int:" +i);
        System.out.println("double:" + d);
        // Explicit type casting(Widening conversion)
        double another_D = 15.75;
        int another_i = (int) another_D;
        System.out.println("Explicit type casting(Narrowing conversion):");
        System.out.println("double:" + another_D);
        System.out.println("int:" + another_i);
    }
}

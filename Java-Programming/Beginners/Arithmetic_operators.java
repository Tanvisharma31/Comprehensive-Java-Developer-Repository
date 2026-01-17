package Beginners;

import java.util.Scanner;

public class Arithmetic_operators {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        // input two numbers from the user using Scanner class
        System.out.println("Enter two numbers:");
        double num1 = scan.nextDouble();
        double num2 = scan.nextDouble();
        //  perform arithmetic operations on the numbers
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        double quotient = num1 / num2;
        // display the results
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);

        //increment and decrement operations
        int a = 5;
        System.out.println("Value of a before increment: " + a);
        System.out.println("Value of a after increment: " + ++a);
        System.out.println("Value of a after decrement: " + --a);
        scan.close();
    }
}

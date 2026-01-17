package Beginners;

public class Exercise {

    public static float intToFloat(int num) {
        // Convert the integer num to a float
        float f = num;
        // Return the float value
        return f;
    }

    public static int stringToInt(String str) {
        // Convert the string str to an integer
        int i = Integer.parseInt(str);
        // Return the integer value
        return i;
    }

    public static void main(String[] args) {
        // Test the intToFloat method
        int num = 10;
        float floatResult = intToFloat(num);
        System.out.println("intToFloat: " + floatResult);

        // Test the stringToInt method
        String str = "100";
        int intResult = stringToInt(str);
        System.out.println("stringToInt: " + intResult);
    }
}

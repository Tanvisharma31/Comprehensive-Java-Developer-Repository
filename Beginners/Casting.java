package Beginners;

public class Casting {
    public static void main(String[] args) {
        // Casting example
        int x = 10;
        double y = x; // No need to use casting here, automatic conversion is possible
        
        // Invalid casting example
        // int z = y;  This will cause an error because y is a double and cannot be assigned to an int

        //Widening casting example
        double d = 5.5;
        int i = (int) d; // Explicit casting is required to convert double to int
        float f = (float) d; // Explicit casting is required to convert double to float
        String s = String.valueOf(d); // Explicit casting is required to convert double to String
        // Narrowing casting example
        int j = 10;
        double e = j; // No need to use casting here, automatic conversion is possible
        float g = j; // No need to use casting here, automatic conversion is possible
        
        String t ="1";
        int h = Integer.parseInt(t); // Explicit casting is required to convert String to int   
        float k = Float.parseFloat(t); // Explicit casting is required to convert String to float
    }
}

package Programming_Fundamentals;

/**
 * Recursion in Programming
 * 
 * Recursion is a technique where a function calls itself.
 * It's useful for solving problems that can be broken down into
 * smaller, similar subproblems.
 * 
 * Key concepts:
 * - Base case: Condition that stops recursion
 * - Recursive case: Function calls itself with modified parameters
 * - Call stack: How recursive calls are managed
 */

public class Recursion {
    
    /**
     * Factorial: n! = n * (n-1) * (n-2) * ... * 1
     * 
     * Base case: factorial(0) = 1, factorial(1) = 1
     * Recursive case: factorial(n) = n * factorial(n-1)
     */
    public static int factorial(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }
        // Recursive case
        return n * factorial(n - 1);
    }
    
    /**
     * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, ...
     * Each number is the sum of the two preceding ones
     * 
     * Base case: fib(0) = 0, fib(1) = 1
     * Recursive case: fib(n) = fib(n-1) + fib(n-2)
     */
    public static int fibonacci(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * Binary Search using recursion
     * Searches for a value in a sorted array
     */
    public static int binarySearch(int[] arr, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        // Base case: element found
        if (arr[mid] == target) {
            return mid;
        }
        
        // Recursive cases
        if (arr[mid] > target) {
            return binarySearch(arr, target, left, mid - 1);
        } else {
            return binarySearch(arr, target, mid + 1, right);
        }
    }
    
    /**
     * Tower of Hanoi
     * Move n disks from source to destination using auxiliary rod
     */
    public static void towerOfHanoi(int n, char source, char destination, char auxiliary) {
        // Base case: only one disk
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        
        // Move n-1 disks from source to auxiliary
        towerOfHanoi(n - 1, source, auxiliary, destination);
        
        // Move the largest disk from source to destination
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        
        // Move n-1 disks from auxiliary to destination
        towerOfHanoi(n - 1, auxiliary, destination, source);
    }
    
    /**
     * Calculate power: x^n
     */
    public static double power(double x, int n) {
        // Base case
        if (n == 0) {
            return 1;
        }
        
        // Handle negative exponent
        if (n < 0) {
            return 1 / power(x, -n);
        }
        
        // Recursive case
        return x * power(x, n - 1);
    }
    
    /**
     * Reverse a string using recursion
     */
    public static String reverse(String str) {
        // Base case
        if (str == null || str.length() <= 1) {
            return str;
        }
        
        // Recursive case: last character + reverse of rest
        return str.charAt(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
    }
    
    /**
     * Check if a string is a palindrome
     */
    public static boolean isPalindrome(String str) {
        return isPalindromeHelper(str, 0, str.length() - 1);
    }
    
    private static boolean isPalindromeHelper(String str, int left, int right) {
        // Base case: single character or empty
        if (left >= right) {
            return true;
        }
        
        // Base case: characters don't match
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        // Recursive case: check inner substring
        return isPalindromeHelper(str, left + 1, right - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Recursion Examples ===\n");
        
        // Factorial
        System.out.println("1. Factorial:");
        for (int i = 0; i <= 5; i++) {
            System.out.println("factorial(" + i + ") = " + factorial(i));
        }
        
        // Fibonacci
        System.out.println("\n2. Fibonacci:");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        
        // Binary Search
        System.out.println("\n3. Binary Search:");
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;
        int index = binarySearch(sortedArray, target, 0, sortedArray.length - 1);
        System.out.println("Found " + target + " at index: " + index);
        
        // Tower of Hanoi
        System.out.println("\n4. Tower of Hanoi (3 disks):");
        towerOfHanoi(3, 'A', 'C', 'B');
        
        // Power
        System.out.println("\n5. Power:");
        System.out.println("2^5 = " + power(2, 5));
        System.out.println("3^-2 = " + power(3, -2));
        
        // Reverse String
        System.out.println("\n6. Reverse String:");
        String original = "Hello";
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reverse(original));
        
        // Palindrome
        System.out.println("\n7. Palindrome Check:");
        String[] testStrings = {"racecar", "hello", "level", "java"};
        for (String str : testStrings) {
            System.out.println(str + " is palindrome: " + isPalindrome(str));
        }
        
        System.out.println("\n=== Recursion Concepts ===");
        System.out.println("✓ Base Case: Stops recursion");
        System.out.println("✓ Recursive Case: Calls itself");
        System.out.println("✓ Call Stack: Manages recursive calls");
        System.out.println("✓ Stack Overflow: Too many recursive calls");
        System.out.println("✓ Tail Recursion: Optimized recursion");
    }
}

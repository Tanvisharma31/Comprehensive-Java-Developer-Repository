package Intermediate;

import java.util.*;

/**
 * Java Generics
 * 
 * Generics provide type safety and eliminate the need for casting.
 * They allow you to write code that works with different types.
 * 
 * Key concepts:
 * - Generic classes
 * - Generic methods
 * - Bounded type parameters
 * - Wildcards (?, ? extends, ? super)
 */

// Generic class
class Box<T> {
    private T item;
    
    public void setItem(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
    
    // Generic method
    public <U> void printItemType(U item) {
        System.out.println("Item type: " + item.getClass().getName());
    }
}

// Bounded type parameter - T must extend Number
class NumberBox<T extends Number> {
    private T number;
    
    public NumberBox(T number) {
        this.number = number;
    }
    
    public double getDoubleValue() {
        return number.doubleValue();
    }
    
    public T getNumber() {
        return number;
    }
}

// Multiple bounds
interface Drawable {
    void draw();
}

interface Colorable {
    void setColor(String color);
}

class ColoredBox<T extends Number & Comparable<T>> {
    private T value;
    
    public ColoredBox(T value) {
        this.value = value;
    }
    
    public int compareTo(ColoredBox<T> other) {
        return this.value.compareTo(other.value);
    }
}

public class Generics {
    
    // Generic method example
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Bounded generic method
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
    
    // Wildcard examples
    public static void printList(List<?> list) {
        // Can read but not write (except null)
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    // Upper bounded wildcard - ? extends Number
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }
    
    // Lower bounded wildcard - ? super Integer
    public static void addNumbers(List<? super Integer> list) {
        // Can add Integer and its subtypes
        list.add(1);
        list.add(2);
        list.add(3);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java Generics Demo ===\n");
        
        // ========== Generic Class ==========
        System.out.println("1. Generic Class:");
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello, Generics!");
        System.out.println("String box: " + stringBox.getItem());
        
        Box<Integer> intBox = new Box<>();
        intBox.setItem(42);
        System.out.println("Integer box: " + intBox.getItem());
        
        // Type safety - this would cause compile error:
        // stringBox.setItem(42); // Error!
        
        // ========== Generic Method ==========
        System.out.println("\n2. Generic Method:");
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"Apple", "Banana", "Cherry"};
        
        System.out.print("Integer array: ");
        printArray(intArray);
        System.out.print("String array: ");
        printArray(stringArray);
        
        // ========== Bounded Type Parameters ==========
        System.out.println("\n3. Bounded Type Parameters:");
        NumberBox<Integer> intNumberBox = new NumberBox<>(100);
        NumberBox<Double> doubleNumberBox = new NumberBox<>(3.14);
        
        System.out.println("Integer value as double: " + intNumberBox.getDoubleValue());
        System.out.println("Double value: " + doubleNumberBox.getDoubleValue());
        
        // This would cause compile error:
        // NumberBox<String> stringNumberBox = new NumberBox<>("Hello"); // Error!
        
        // ========== Bounded Generic Method ==========
        System.out.println("\n4. Bounded Generic Method:");
        Integer[] numbers = {5, 2, 8, 1, 9};
        String[] words = {"zebra", "apple", "banana"};
        
        System.out.println("Max integer: " + findMax(numbers));
        System.out.println("Max string: " + findMax(words));
        
        // ========== Wildcards ==========
        System.out.println("\n5. Wildcards:");
        
        List<String> stringList = Arrays.asList("A", "B", "C");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        
        System.out.print("String list: ");
        printList(stringList);
        System.out.print("Integer list: ");
        printList(intList);
        
        // Upper bounded wildcard
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("Sum of integers: " + sumNumbers(integers));
        System.out.println("Sum of doubles: " + sumNumbers(doubles));
        
        // Lower bounded wildcard
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println("Numbers added: " + numberList);
        
        // ========== Generic Collections ==========
        System.out.println("\n6. Generic Collections:");
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Alice", 25);
        ageMap.put("Bob", 30);
        System.out.println("Age map: " + ageMap);
        
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("John");
        uniqueNames.add("Jane");
        uniqueNames.add("John"); // Duplicate ignored
        System.out.println("Unique names: " + uniqueNames);
        
        System.out.println("\n=== Generics Benefits ===");
        System.out.println("✓ Type safety - catch errors at compile time");
        System.out.println("✓ No casting needed - cleaner code");
        System.out.println("✓ Code reuse - write once, use with any type");
        System.out.println("✓ Better performance - no runtime type checking");
    }
}

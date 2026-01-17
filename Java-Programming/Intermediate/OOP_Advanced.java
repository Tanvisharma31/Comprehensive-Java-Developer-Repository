package Intermediate;

/**
 * Advanced Object-Oriented Programming Concepts in Java
 * 
 * This class demonstrates:
 * - Inheritance
 * - Polymorphism
 * - Abstraction
 * - Encapsulation
 * - Method overriding
 * - Abstract classes
 * - Interfaces
 */

// Abstract class - cannot be instantiated directly
abstract class Animal {
    // Encapsulation: private fields with public getters/setters
    private String name;
    private int age;
    
    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getters and Setters (Encapsulation)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void makeSound();
    
    // Concrete method - can be used by all subclasses
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
}

// Interface - defines a contract
interface Flyable {
    void fly();
    default void land() {
        System.out.println("Landing...");
    }
}

interface Swimmable {
    void swim();
}

// Inheritance: Dog extends Animal
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age); // Call parent constructor
        this.breed = breed;
    }
    
    // Polymorphism: Method overriding
    @Override
    public void makeSound() {
        System.out.println(getName() + " barks: Woof! Woof!");
    }
    
    // Specific method for Dog
    public void fetch() {
        System.out.println(getName() + " is fetching the ball!");
    }
    
    public String getBreed() {
        return breed;
    }
}

// Inheritance: Cat extends Animal
class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
    
    // Polymorphism: Method overriding
    @Override
    public void makeSound() {
        System.out.println(getName() + " meows: Meow! Meow!");
    }
}

// Multiple inheritance through interfaces
class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name, int age) {
        super(name, age);
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + " quacks: Quack! Quack!");
    }
    
    @Override
    public void fly() {
        System.out.println(getName() + " is flying!");
    }
    
    @Override
    public void swim() {
        System.out.println(getName() + " is swimming!");
    }
}

public class OOP_Advanced {
    public static void main(String[] args) {
        System.out.println("=== Advanced OOP Concepts Demo ===\n");
        
        // Polymorphism: Using parent reference for child objects
        Animal dog = new Dog("Buddy", 3, "Golden Retriever");
        Animal cat = new Cat("Whiskers", 2);
        Animal duck = new Duck("Donald", 1);
        
        // Polymorphic method calls
        System.out.println("1. Polymorphism - Same method, different behaviors:");
        dog.makeSound();
        cat.makeSound();
        duck.makeSound();
        
        System.out.println("\n2. Encapsulation - Accessing private fields through methods:");
        System.out.println("Dog name: " + dog.getName());
        System.out.println("Dog age: " + dog.getAge());
        
        System.out.println("\n3. Inheritance - Using parent class methods:");
        dog.sleep();
        cat.sleep();
        
        System.out.println("\n4. Interface Implementation:");
        if (duck instanceof Flyable) {
            ((Flyable) duck).fly();
        }
        if (duck instanceof Swimmable) {
            ((Swimmable) duck).swim();
        }
        
        System.out.println("\n5. Type-specific methods:");
        if (dog instanceof Dog) {
            ((Dog) dog).fetch();
            System.out.println("Breed: " + ((Dog) dog).getBreed());
        }
        
        System.out.println("\n=== Key OOP Principles ===");
        System.out.println("✓ Encapsulation: Data hiding with private fields");
        System.out.println("✓ Inheritance: Code reuse through parent-child relationship");
        System.out.println("✓ Polymorphism: Same interface, different implementations");
        System.out.println("✓ Abstraction: Hiding complexity through abstract classes/interfaces");
    }
}

package Advanced;

import java.lang.reflect.*;

/**
 * Java Reflection API
 * 
 * Reflection allows you to inspect and manipulate classes, methods, fields
 * at runtime. It's powerful but should be used carefully.
 * 
 * Use cases:
 * - Framework development
 * - Testing frameworks
 * - Dependency injection
 * - Serialization/Deserialization
 */

class SampleClass {
    private String privateField = "Private Value";
    public String publicField = "Public Value";
    
    public SampleClass() {
        System.out.println("Default constructor called");
    }
    
    public SampleClass(String value) {
        this.publicField = value;
        System.out.println("Parameterized constructor called with: " + value);
    }
    
    public void publicMethod() {
        System.out.println("Public method called");
    }
    
    private void privateMethod() {
        System.out.println("Private method called");
    }
    
    public String methodWithParams(String param) {
        return "Method called with: " + param;
    }
}

public class Reflection {
    
    public static void inspectClass(Class<?> clazz) {
        System.out.println("=== Class Inspection ===");
        System.out.println("Class Name: " + clazz.getName());
        System.out.println("Simple Name: " + clazz.getSimpleName());
        System.out.println("Package: " + clazz.getPackage());
        System.out.println("Superclass: " + clazz.getSuperclass());
        
        // Get interfaces
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("Interfaces: " + java.util.Arrays.toString(interfaces));
        
        // Get modifiers
        int modifiers = clazz.getModifiers();
        System.out.println("Is Public: " + Modifier.isPublic(modifiers));
        System.out.println("Is Abstract: " + Modifier.isAbstract(modifiers));
    }
    
    public static void inspectFields(Class<?> clazz) {
        System.out.println("\n=== Field Inspection ===");
        Field[] fields = clazz.getDeclaredFields();
        
        for (Field field : fields) {
            System.out.println("Field: " + field.getName());
            System.out.println("  Type: " + field.getType());
            System.out.println("  Modifiers: " + Modifier.toString(field.getModifiers()));
        }
    }
    
    public static void inspectMethods(Class<?> clazz) {
        System.out.println("\n=== Method Inspection ===");
        Method[] methods = clazz.getDeclaredMethods();
        
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
            System.out.println("  Return Type: " + method.getReturnType());
            System.out.println("  Parameters: " + java.util.Arrays.toString(method.getParameterTypes()));
            System.out.println("  Modifiers: " + Modifier.toString(method.getModifiers()));
        }
    }
    
    public static void inspectConstructors(Class<?> clazz) {
        System.out.println("\n=== Constructor Inspection ===");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor.getName());
            System.out.println("  Parameters: " + java.util.Arrays.toString(constructor.getParameterTypes()));
            System.out.println("  Modifiers: " + Modifier.toString(constructor.getModifiers()));
        }
    }
    
    public static void createInstance() throws Exception {
        System.out.println("\n=== Creating Instance ===");
        
        Class<SampleClass> clazz = SampleClass.class;
        
        // Create instance using default constructor
        SampleClass obj1 = clazz.getDeclaredConstructor().newInstance();
        
        // Create instance using parameterized constructor
        SampleClass obj2 = clazz.getDeclaredConstructor(String.class)
            .newInstance("Reflection Value");
    }
    
    public static void accessFields() throws Exception {
        System.out.println("\n=== Accessing Fields ===");
        
        SampleClass obj = new SampleClass();
        Class<?> clazz = obj.getClass();
        
        // Access public field
        Field publicField = clazz.getField("publicField");
        System.out.println("Public field value: " + publicField.get(obj));
        publicField.set(obj, "New Public Value");
        System.out.println("Public field value after set: " + publicField.get(obj));
        
        // Access private field
        Field privateField = clazz.getDeclaredField("privateField");
        privateField.setAccessible(true); // Make accessible
        System.out.println("Private field value: " + privateField.get(obj));
        privateField.set(obj, "New Private Value");
        System.out.println("Private field value after set: " + privateField.get(obj));
    }
    
    public static void invokeMethods() throws Exception {
        System.out.println("\n=== Invoking Methods ===");
        
        SampleClass obj = new SampleClass();
        Class<?> clazz = obj.getClass();
        
        // Invoke public method
        Method publicMethod = clazz.getMethod("publicMethod");
        publicMethod.invoke(obj);
        
        // Invoke method with parameters
        Method methodWithParams = clazz.getMethod("methodWithParams", String.class);
        String result = (String) methodWithParams.invoke(obj, "Reflection Parameter");
        System.out.println("Method result: " + result);
        
        // Invoke private method
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(obj);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("=== Java Reflection API Demo ===\n");
            
            Class<SampleClass> clazz = SampleClass.class;
            
            inspectClass(clazz);
            inspectFields(clazz);
            inspectMethods(clazz);
            inspectConstructors(clazz);
            createInstance();
            accessFields();
            invokeMethods();
            
            System.out.println("\n=== Reflection Use Cases ===");
            System.out.println("✓ Framework development (Spring, Hibernate)");
            System.out.println("✓ Testing frameworks (JUnit)");
            System.out.println("✓ Serialization/Deserialization");
            System.out.println("✓ Dependency injection");
            System.out.println("✓ Code analysis tools");
            
            System.out.println("\n=== Reflection Considerations ===");
            System.out.println("⚠ Performance overhead");
            System.out.println("⚠ Security concerns (accessing private members)");
            System.out.println("⚠ Breaks encapsulation");
            System.out.println("⚠ Harder to maintain");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

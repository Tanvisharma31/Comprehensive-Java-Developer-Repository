package Intermediate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java Streams API (Java 8+)
 * 
 * Streams provide a functional approach to processing collections.
 * They enable declarative, functional-style operations on data.
 * 
 * Key concepts:
 * - Stream creation
 * - Intermediate operations (filter, map, sorted, etc.)
 * - Terminal operations (collect, forEach, reduce, etc.)
 * - Parallel streams
 */

class Person {
    private String name;
    private int age;
    private String city;
    
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCity() { return city; }
    
    @Override
    public String toString() {
        return name + " (" + age + ", " + city + ")";
    }
}

public class Streams_API {
    public static void main(String[] args) {
        System.out.println("=== Java Streams API Demo ===\n");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "New York"),
            new Person("Bob", 30, "London"),
            new Person("Charlie", 28, "New York"),
            new Person("Diana", 22, "Paris"),
            new Person("Eve", 35, "London"),
            new Person("Frank", 27, "Tokyo")
        );
        
        // ========== Stream Creation ==========
        System.out.println("1. Stream Creation:");
        Stream<String> stream1 = Stream.of("A", "B", "C");
        Stream<Integer> stream2 = Arrays.asList(1, 2, 3).stream();
        Stream<Integer> stream3 = Stream.iterate(0, n -> n + 2).limit(5);
        
        System.out.print("Stream.of: ");
        stream1.forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        System.out.print("List.stream: ");
        stream2.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.print("Stream.iterate: ");
        stream3.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // ========== Filter ==========
        System.out.println("\n2. Filter - Select elements matching condition:");
        List<Person> adults = people.stream()
            .filter(p -> p.getAge() >= 25)
            .collect(Collectors.toList());
        System.out.println("Adults (age >= 25): " + adults);
        
        List<Person> fromNY = people.stream()
            .filter(p -> p.getCity().equals("New York"))
            .collect(Collectors.toList());
        System.out.println("From New York: " + fromNY);
        
        // ========== Map ==========
        System.out.println("\n3. Map - Transform elements:");
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        System.out.println("Names: " + names);
        
        List<Integer> ages = people.stream()
            .map(Person::getAge)
            .collect(Collectors.toList());
        System.out.println("Ages: " + ages);
        
        // ========== Sorted ==========
        System.out.println("\n4. Sorted - Sort elements:");
        List<Person> sortedByAge = people.stream()
            .sorted(Comparator.comparing(Person::getAge))
            .collect(Collectors.toList());
        System.out.println("Sorted by age: " + sortedByAge);
        
        List<Person> sortedByName = people.stream()
            .sorted(Comparator.comparing(Person::getName))
            .collect(Collectors.toList());
        System.out.println("Sorted by name: " + sortedByName);
        
        // ========== Distinct ==========
        System.out.println("\n5. Distinct - Remove duplicates:");
        List<String> cities = people.stream()
            .map(Person::getCity)
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Unique cities: " + cities);
        
        // ========== Limit and Skip ==========
        System.out.println("\n6. Limit and Skip:");
        List<Person> first3 = people.stream()
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("First 3: " + first3);
        
        List<Person> skipFirst2 = people.stream()
            .skip(2)
            .collect(Collectors.toList());
        System.out.println("Skip first 2: " + skipFirst2);
        
        // ========== Terminal Operations ==========
        System.out.println("\n7. Terminal Operations:");
        
        // Count
        long count = people.stream().count();
        System.out.println("Total people: " + count);
        
        // AnyMatch, AllMatch, NoneMatch
        boolean hasYoung = people.stream().anyMatch(p -> p.getAge() < 25);
        System.out.println("Has person under 25: " + hasYoung);
        
        boolean allAdults = people.stream().allMatch(p -> p.getAge() >= 18);
        System.out.println("All adults: " + allAdults);
        
        // FindFirst, FindAny
        Optional<Person> first = people.stream()
            .filter(p -> p.getCity().equals("London"))
            .findFirst();
        System.out.println("First from London: " + first.orElse(null));
        
        // ========== Reduce ==========
        System.out.println("\n8. Reduce - Aggregate values:");
        int sumOfAges = people.stream()
            .mapToInt(Person::getAge)
            .sum();
        System.out.println("Sum of ages: " + sumOfAges);
        
        Optional<Integer> maxAge = people.stream()
            .map(Person::getAge)
            .reduce(Integer::max);
        System.out.println("Max age: " + maxAge.orElse(0));
        
        // ========== Collectors ==========
        System.out.println("\n9. Collectors - Advanced collection:");
        
        // Grouping
        Map<String, List<Person>> byCity = people.stream()
            .collect(Collectors.groupingBy(Person::getCity));
        System.out.println("Grouped by city: " + byCity);
        
        // Partitioning
        Map<Boolean, List<Person>> partitioned = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
        System.out.println("Partitioned by age >= 30: " + partitioned);
        
        // Joining
        String allNames = people.stream()
            .map(Person::getName)
            .collect(Collectors.joining(", "));
        System.out.println("All names: " + allNames);
        
        // ========== Parallel Streams ==========
        System.out.println("\n10. Parallel Streams:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        int sum = numbers.parallelStream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum using parallel stream: " + sum);
        
        System.out.println("\n=== Streams Benefits ===");
        System.out.println("✓ Declarative - what to do, not how");
        System.out.println("✓ Functional - immutable, no side effects");
        System.out.println("✓ Composable - chain operations");
        System.out.println("✓ Lazy - operations executed only when needed");
        System.out.println("✓ Parallelizable - easy parallel processing");
    }
}

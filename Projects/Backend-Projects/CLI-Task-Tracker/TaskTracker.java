import java.io.*;
import java.util.*;

/**
 * CLI Task Tracker
 * A simple command-line task management application
 */
class Task {
    private int id;
    private String description;
    private boolean completed;
    
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }
    
    public int getId() { return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    @Override
    public String toString() {
        return id + ". [" + (completed ? "X" : " ") + "] " + description;
    }
}

public class TaskTracker {
    private List<Task> tasks;
    private int nextId;
    private static final String DATA_FILE = "tasks.txt";
    
    public TaskTracker() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
        loadTasks();
    }
    
    public void addTask(String description) {
        Task task = new Task(nextId++, description);
        tasks.add(task);
        System.out.println("Task added: " + description + " (ID: " + task.getId() + ")");
    }
    
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        
        System.out.println("\nTasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
    
    public void completeTask(int id) {
        Task task = findTask(id);
        if (task != null) {
            task.setCompleted(true);
            System.out.println("Task " + id + " marked as complete");
        } else {
            System.out.println("Task " + id + " not found");
        }
    }
    
    public void deleteTask(int id) {
        Task task = findTask(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task " + id + " deleted");
        } else {
            System.out.println("Task " + id + " not found");
        }
    }
    
    private Task findTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    
    public void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Task task : tasks) {
                writer.println(task.getId() + "|" + task.getDescription() + "|" + task.isCompleted());
            }
            System.out.println("Tasks saved to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    public void loadTasks() {
        try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String description = parts[1];
                    boolean completed = Boolean.parseBoolean(parts[2]);
                    
                    Task task = new Task(id, description);
                    task.setCompleted(completed);
                    tasks.add(task);
                    
                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            }
            System.out.println("Tasks loaded from " + DATA_FILE);
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, that's okay
        } catch (Exception e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        TaskTracker tracker = new TaskTracker();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== CLI Task Tracker ===");
        System.out.println("Commands: add <desc>, list, complete <id>, delete <id>, save, load, exit");
        
        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) continue;
            
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();
            
            switch (command) {
                case "add":
                    if (parts.length > 1) {
                        tracker.addTask(parts[1]);
                    } else {
                        System.out.println("Usage: add <description>");
                    }
                    break;
                    
                case "list":
                    tracker.listTasks();
                    break;
                    
                case "complete":
                    if (parts.length > 1) {
                        try {
                            int id = Integer.parseInt(parts[1]);
                            tracker.completeTask(id);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid task ID");
                        }
                    } else {
                        System.out.println("Usage: complete <id>");
                    }
                    break;
                    
                case "delete":
                    if (parts.length > 1) {
                        try {
                            int id = Integer.parseInt(parts[1]);
                            tracker.deleteTask(id);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid task ID");
                        }
                    } else {
                        System.out.println("Usage: delete <id>");
                    }
                    break;
                    
                case "save":
                    tracker.saveTasks();
                    break;
                    
                case "load":
                    tracker.loadTasks();
                    break;
                    
                case "exit":
                    tracker.saveTasks();
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Unknown command: " + command);
                    System.out.println("Commands: add, list, complete, delete, save, load, exit");
            }
        }
    }
}

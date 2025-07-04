import java.util.List;
import java.util.Scanner;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        manager.addTask("Task 1");
        manager.addTask("Task 2");

        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n===== Task Manager =====");
            System.out.println("1- Add new task");
            System.out.println("2- Show all tasks");
            System.out.println("3- Show tasks by status");
            System.out.println("4- Edit task");
            System.out.println("5- Delete task");
            System.out.println("0- Exit");

            int choice = safeInput();
            switch (choice) {
                case 1 -> addTaskMenu();
                case 2 -> printTasks(manager.getTasks());
                case 3 -> showTasksByStatus();
                case 4 -> editTaskMenu();
                case 5 -> deleteTaskMenu();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    private static void addTaskMenu() {
        System.out.print("Enter task description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        manager.addTask(description);
    }

    private static void showTasksByStatus() {
        System.out.println("1- TO DO\n2- DOING\n3- DONE");
        int choice = safeInput();
        Condition status = switch (choice) {
            case 1 -> Condition.TO_DO;
            case 2 -> Condition.DOING;
            case 3 -> Condition.DONE;
            default -> {
                System.out.println("Invalid choice");
                yield null;
            }
        };
        if (status != null)
            printTasks(manager.getTasksByStatus(status));
    }

    private static void editTaskMenu() {
        printTasks(manager.getTasks());
        System.out.print("Enter task number to edit: ");
        int index = safeInput() - 1;
        if (isValidIndex(index)) {
            Task task = manager.getTasks().get(index);
            System.out.print("New description (leave blank to skip): ");
            scanner.nextLine();
            String desc = scanner.nextLine();
            if (!desc.isBlank())
                task.setDescription(desc);

            System.out.println("Change status? 1-TO DO  2-DOING  3-DONE  0-Skip");
            int statusChoice = safeInput();
            switch (statusChoice) {
                case 1 -> task.setStatus(Condition.TO_DO);
                case 2 -> task.setStatus(Condition.DOING);
                case 3 -> task.setStatus(Condition.DONE);
            }
        }
    }

    private static void deleteTaskMenu() {
        printTasks(manager.getTasks());
        System.out.print("Enter task number to delete: ");
        int index = safeInput() - 1;
        if (isValidIndex(index))
            manager.deleteTask(index);
    }

    private static void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ") " + tasks.get(i));
        }
    }

    private static int safeInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean isValidIndex(int index) {
        if (index < 0 || index >= manager.getTasks().size()) {
            System.out.println("Invalid task number.");
            return false;
        }
        return true;
    }
}

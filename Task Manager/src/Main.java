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
            System.out.println("\n===== Task Manager =====\n" +
                    "1- Add new task\n" +
                    "2- Show all tasks\n" +
                    "3- Show tasks by status\n" +
                    "4- Edit task\n" +
                    "5- Delete task\n" +
                    "0- Exit\n");

            int choice = safeInput();
            switch (choice) {
                case 1:
                    addTaskMenu();
                    break;
                case 2:
                    printTasks(manager.getTasks());
                    break;
                case 3:
                    showTasksByStatus();
                    break;
                case 4:
                    editTaskMenu();
                    break;
                case 5:
                    deleteTaskMenu();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
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
        Condition status = null;

        switch (choice) {
            case 1:
                status = Condition.TO_DO;
                break;
            case 2:
                status = Condition.DOING;
                break;
            case 3:
                status = Condition.DONE;
                break;
            default:
                System.out.println("Invalid choice");
        }

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
                case 1:
                    task.setStatus(Condition.TO_DO);
                    break;
                case 2:
                    task.setStatus(Condition.DOING);
                    break;
                case 3:
                    task.setStatus(Condition.DONE);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
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
            System.out.print("Invalid input :( , enter a number ");
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

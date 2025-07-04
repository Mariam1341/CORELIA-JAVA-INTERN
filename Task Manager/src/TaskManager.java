import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getTasksByStatus(Condition status) {
        return tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }
}

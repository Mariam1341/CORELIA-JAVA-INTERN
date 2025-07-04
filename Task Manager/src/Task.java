import java.util.ArrayList;
import java.util.List;
public class Task {
    private String description;
    private Condition status;

    public Task(String description) {
        this.description = description;
        this.status = Condition.TO_DO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getStatus() {
        return status;
    }

    public void setStatus(Condition status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return description + "  -- STATUS : " + status;
    }
}

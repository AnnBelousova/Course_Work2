import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private String taskName;
    private String description;
    private String taskType;
    private LocalDateTime localDate;
    private String frequency;
    private Boolean isActive;

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return taskType;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }


    public String getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return taskName + ", " + description + ", " + taskType + ", " + localDate + ", " + frequency;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Task(String taskName, String description, String taskType, LocalDateTime localDate, String frequency, Boolean isActive) {
        this.taskName = taskName;
        this.description = description;
        this.taskType = taskType;
        this.localDate = localDate;
        this.frequency = frequency;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName) && Objects.equals(description, task.description) && Objects.equals(taskType, task.taskType) && Objects.equals(localDate, task.localDate) && Objects.equals(frequency, task.frequency) && Objects.equals(isActive, task.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, description, taskType, localDate, frequency, isActive);
    }
}

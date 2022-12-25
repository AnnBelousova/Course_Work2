import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface Frequency {
    Set<Task> getNextDate(LocalDate localDate, Map<Integer, Task> taskList);
}

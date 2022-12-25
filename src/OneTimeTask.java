import java.time.LocalDate;
import java.util.*;

public class OneTimeTask implements Frequency {
    @Override
    public Set<Task> getNextDate(LocalDate localDate, Map<Integer, Task> taskList) {
        Set<Task> list = new HashSet<>();
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
}

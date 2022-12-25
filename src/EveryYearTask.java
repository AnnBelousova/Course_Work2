import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EveryYearTask implements Frequency {
    @Override
    public Set<Task> getNextDate(LocalDate localDate, Map<Integer, Task> taskList) {
        Set<Task> list = new HashSet<>();
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            LocalDateTime taskDateEveryEar = LocalDateTime.of(entry.getValue().getLocalDate().getYear() + 1,
                    entry.getValue().getLocalDate().getMonth(), entry.getValue().getLocalDate().getDayOfMonth(),
                    entry.getValue().getLocalDate().getHour(), entry.getValue().getLocalDate().getMinute());
            Task newTaskEveryDay = new Task(entry.getValue().getTaskName(), entry.getValue().getDescription(), entry.getValue().getTaskType(), taskDateEveryEar, entry.getValue().getFrequency(), entry.getValue().getActive());
            list.add(newTaskEveryDay);
        }
        return list;
    }
}

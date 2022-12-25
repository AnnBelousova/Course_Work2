import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EveryDayTask implements Frequency {
    @Override
    public Set<Task> getNextDate(LocalDate localDate, Map<Integer, Task> taskList) {
        Set<Task> list = new HashSet<>();
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            LocalDateTime taskDateEveryDay = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), entry.getValue().getLocalDate().getHour(), entry.getValue().getLocalDate().getMinute());
            Task newTaskEveryDay = new Task(entry.getValue().getTaskName(), entry.getValue().getDescription(), entry.getValue().getTaskType(), taskDateEveryDay, entry.getValue().getFrequency(), entry.getValue().getActive());
            list.add(newTaskEveryDay);
        }
        return list;
    }
}

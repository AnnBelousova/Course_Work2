import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EveryWeekTask implements Frequency {
    @Override
    public Set<Task> getNextDate(LocalDate localDate, Map<Integer, Task> taskList) {
        Set<Task> list = new HashSet<>();
        List<Task> tempList = new ArrayList<>();
        LocalDateTime tempDate;
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            for (int i = entry.getValue().getLocalDate().getDayOfMonth(); i < 31; i = i + 7) {
                tempDate = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), i, entry.getValue().getLocalDate().getHour(), entry.getValue().getLocalDate().getMinute());
                tempList.add(new Task(entry.getValue().getTaskName(), entry.getValue().getDescription(), entry.getValue().getTaskType(), tempDate, entry.getValue().getFrequency(), entry.getValue().getActive()));
            }
            for (int i = entry.getValue().getLocalDate().getDayOfMonth() - 7; i > 0; i = i - 7) {
                tempDate = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), i, entry.getValue().getLocalDate().getHour(), entry.getValue().getLocalDate().getMinute());
                tempList.add(new Task(entry.getValue().getTaskName(), entry.getValue().getDescription(), entry.getValue().getTaskType(), tempDate, entry.getValue().getFrequency(), entry.getValue().getActive()));
            }
            for (Task t : tempList) {
                if (t.getLocalDate().getYear() == localDate.getYear() && t.getLocalDate().getMonthValue() == localDate.getMonthValue() && t.getLocalDate().getDayOfMonth() == localDate.getDayOfMonth()) {
                    list.add(t);
                }
            }
        }
        return list;
    }
}

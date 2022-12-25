import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EveryMonthTask implements Frequency {

    @Override
    public Set<Task> getNextDate(LocalDate localDate, Map<Integer, Task> taskList) {
        Set<Task> list = new HashSet<>();
        List<Task> tempList = new ArrayList<>();
        LocalDateTime tempDate;
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            for (int i = entry.getValue().getLocalDate().getMonthValue(); i < 13; i++) {
                tempDate = LocalDateTime.of(localDate.getYear(), i, entry.getValue().getLocalDate().getDayOfMonth(), entry.getValue().getLocalDate().getHour(), entry.getValue().getLocalDate().getMinute());
                tempList.add(new Task(entry.getValue().getTaskName(), entry.getValue().getDescription(), entry.getValue().getTaskType(), tempDate, entry.getValue().getFrequency(), entry.getValue().getActive()));
            }
            for (int i = entry.getValue().getLocalDate().getMonthValue() - 1; i > 0; i--) {
                tempDate = LocalDateTime.of(localDate.getYear(), i, entry.getValue().getLocalDate().getDayOfMonth(), entry.getValue().getLocalDate().getHour(), entry.getValue().getLocalDate().getMinute());
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
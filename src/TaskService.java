import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskService {
    Scanner scanner = new Scanner(System.in);
    private String taskName;
    private String taskDescription;
    private String taskFrequency;
    private  String taskType;
    private LocalDateTime taskDate;
    private Boolean isActive = true;
    private int count = 0;
    private Task task;
    private Map<Integer, Task> taskList = new HashMap<>();
    Validation validation = new Validation();
    Frequency oneTimeTask = new OneTimeTask();
    Frequency everyDayTask = new EveryDayTask();
    Frequency everyWeekTask = new EveryWeekTask();
    Frequency everyMonthTask = new EveryMonthTask();
    Frequency everyYearTask = new EveryYearTask();

    public void readTaskName() {
        System.out.println("\tВведите название задачи:");
        taskName = scanner.next();
        validation.throwExceptionStringField(taskName);
    }

    public void readDescription() {
        System.out.println("\tВведите описание задачи:");
        taskDescription = scanner.next();
        validation.throwExceptionStringField(taskDescription);
    }

    public void readTaskFrequency() {
        System.out.println("\tВведите частоту задачи:\n\t\t1 - Однократная\n\t\t2 - Ежедневная" +
                "\n\t\t3 - Еженедельная\n\t\t4 - Ежемесячная\n\t\t5 - Ежегодная");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                taskFrequency = "однократная";
                break;
            case 2:
                taskFrequency = "ежедневная";
                break;
            case 3:
                taskFrequency = "еженедельная";
                break;
            case 4:
                taskFrequency = "ежемесячная";
                break;
            case 5:
                taskFrequency = "ежегодная";
                break;
            default:
                throw new IllegalArgumentException("Неверный выбор");
        }
        validation.throwExceptionStringField(taskFrequency);
    }

    public LocalDateTime readDateOfTask() {
        System.out.println("\tВведите дату задачи в формате: dd/MM/yyyy,HH:mm");
        taskDate = LocalDateTime.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm", Locale.US));
        if (taskDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("\tДата задачи меньше текущей, введите корректную дату");

        }
        return taskDate;
    }

    public void readTaskType() {
        System.out.println("\tВведите тип задачи \n\t\t1 - личная \n\t\t2 - рабочая:");
        int choice = Integer.parseInt(scanner.next());
        switch (choice) {
            case 1:
                taskType = "личная";
                break;
            case 2:
                taskType = "рабочая";
                break;
            default:
                throw new IllegalArgumentException("\tНеверный выбор");
        }
        validation.throwExceptionStringField(taskType);
    }

    public Task fillTask() {
        readTaskName();
        readDescription();
        readTaskType();
        readTaskFrequency();
        readDateOfTask();
        Task task = new Task(taskName, taskDescription, taskType, taskDate, taskFrequency, isActive);
        return task;
    }

    public void deleteTask(Integer id) {
        if (taskList.containsKey(id)) {
            //taskList.remove(id);
            Task taskNonActive = new Task(taskName, taskDescription, taskType, taskDate, taskFrequency, false);
            taskList.replace(id, taskNonActive);
        } else {
            System.out.println("\tТакой задачи нет.");
        }
    }

    public void getDeletedTasks() {
        System.out.println("Удаленные задачи:");
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            if (entry.getValue().getActive() == false) {
                System.out.println(entry.getValue().getTaskName() + ", дата задачи " + entry.getValue().getLocalDate() + ", повторяется " + entry.getValue().getFrequency());
            }
        }
        System.out.println();
    }

    public void addTaskToList() {
        count++;
        task = fillTask();
        taskList.put(count, task);
    }

    public void getTask(LocalDate localDate) {
        Set<Task> list = new HashSet<>();
        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            if (entry.getValue().getFrequency().equals("однократная") &&
                    entry.getValue().getLocalDate().getYear() == localDate.getYear() &&
                    entry.getValue().getLocalDate().getMonthValue() == localDate.getMonthValue() &&
                    entry.getValue().getLocalDate().getDayOfMonth() == localDate.getDayOfMonth()) {
                list.addAll(oneTimeTask.getNextDate(localDate, taskList));

            } else if (entry.getValue().getFrequency().equals("ежедневная")) {
                list.addAll(everyDayTask.getNextDate(localDate, taskList));
            } else if (entry.getValue().getFrequency().equals("еженедельная")) {
                list.addAll(everyWeekTask.getNextDate(localDate, taskList));
            } else if (entry.getValue().getFrequency().equals("ежемесячная")) {
                list.addAll(everyMonthTask.getNextDate(localDate, taskList));
            } else if (entry.getValue().getFrequency().equals("ежегодная")) {
                everyYearTask.getNextDate(localDate, taskList);
            } else {
                System.out.println("\tНет задач на эту дату");
            }
        }
        System.out.println(list);
        System.out.println();
    }

    public void updateTaskById(Integer id) {
        if (taskList.containsKey(id)) {
            System.out.println(taskList.values());
            label:
            while (true) {
                printMenu();
                System.out.println("------------------------------------------------\n" +
                        "Выберите пункт меню:");
                if (scanner.hasNextInt()) {
                    int choice = Integer.parseInt(scanner.next());
                    switch (choice) {
                        case 1:
                            String name = scanner.next();
                            Task taskUpdatedName = new Task(name, taskDescription, taskType, taskDate, taskFrequency, isActive);
                            taskList.replace(id, taskUpdatedName);
                            break;
                        case 2:
                            String description = scanner.next();
                            Task taskUpdatedDescr = new Task(taskName, description, taskType, taskDate, taskFrequency, isActive);
                            taskList.replace(id, taskUpdatedDescr);
                            break;
                        case 0:
                            System.out.println(taskList.values());
                            break label;
                        default:
                            throw new IllegalArgumentException("\tНеверный выбор");
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } else {
            System.out.println("\tТакой задачи нет.");
        }
    }

    public void printMenu() {
        System.out.println("\t1. Редактировать имя,\n" +
                "\t2. ------------ описание,\n" +
                "\t0. Выход.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskService that = (TaskService) o;
        return count == that.count && Objects.equals(taskName, that.taskName) && Objects.equals(taskDescription, that.taskDescription) && Objects.equals(taskFrequency, that.taskFrequency) && Objects.equals(taskType, that.taskType) && Objects.equals(taskDate, that.taskDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskDescription, taskFrequency, taskType, taskDate, count);
    }

}

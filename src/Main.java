import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static TaskService taskService = new TaskService();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("------------------------------------------------\n" +
                        "Выберите пункт меню:");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    int id;
                    switch (menu) {
                        case 1:
                            taskService.addTaskToList();
                            break;
                        case 2:
                            System.out.println("Введите id задачи для удаления:");
                            id = scanner.nextInt();
                            taskService.deleteTask(id);
                            break;
                        case 3:
                            taskService.getDeletedTasks();
                            break;
                        case 4:
                            System.out.println("Введите дату, для поиска задач:");
                            LocalDate localDateScan = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            taskService.getTask(localDateScan);
                            break;
                        case 5:
                            System.out.println("Введите id задачи для редактирования:");
                            id = scanner.nextInt();
                            taskService.updateTaskById(id);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("------------------------------------------------\n" +
                "1. Добавить задачу,\n" +
                "2. Удалить задачу,\n" +
                "3. Вывести список удаленных задач,\n" +
                "4. Получить задачи на указанный день,\n" +
                "5. Редактировать задачу\n" +
                "0. Выход");
    }
}
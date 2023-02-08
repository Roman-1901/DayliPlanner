import Exceptions.IncorrectArgumentException;
import Exceptions.TaskNotFoundException;
import Tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Task test1 = new OneTimeTask("Описание-1", Type.PERSONAL, "Тестовая задача - 1", "08.02.2023 12:00");
        Task test2 = new WeeklyTask("Описание-2", Type.WORK, "Тестовая задача - 2", "08.02.2023 10:00");
        Task test3 = new MonthlyTask("Описание-3", Type.PERSONAL, "Тестовая задача - 3", "08.02.2023 14:30");
        Task test4 = new DailyTask("Описание-4", Type.WORK, "Тестовая задача - 4", "08.02.2023 15:10");
        Task test5 = new YearlyTask("Описание-5", Type.PERSONAL, "Тестовая задача - 5", "08.02.2023 08:40");
        Task test6 = new YearlyTask("Описание-5", Type.PERSONAL, "Тестовая задача - 5", "08.02.2023 08:40");


        TaskService.addTask(test1);
        TaskService.addTask(test2);
        TaskService.addTask(test3);
        TaskService.addTask(test4);
        TaskService.addTask(test5);
        TaskService.addTask(test6);
//-добавлены тестовые задачи, чтобы каждый раз не вводить через консоль

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            try {
                                inputTask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 2:
                            System.out.print("Выберите id задачи для удаления: ");
                            try {
                                delDask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 3:
                            System.out.println("Введите дату [dd.MM.yyyy]: ");
                            try {
                                dateFormat(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }


    //- Метод добавления задачи, указывается наименование, описание, дата, повторяемость и тип. При неправильном вводе программа сообщает об этом, не выкидывает.
    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите название задачи: ");
        scanner.nextLine();
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.println("Введите дату начала задачи [dd.MM.yyyy HH:mm]: ");
        String taskDate = scanner.nextLine();
        Type typeTask;
        label:
        while (true) {
            System.out.println("Выберите повторяемость задачи: ");
            System.out.println("1.Однократная 2.Ежедневная 3.Еженедельная 4.Ежемесячная 5.Ежегодная");
            if (scanner.hasNextInt()) {
                int replayTask = scanner.nextInt();
                switch (replayTask) {
                    case 1:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new OneTimeTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 2:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new DailyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 3:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new WeeklyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 4:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new MonthlyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    case 5:
                        typeTask = typeTask(scanner);
                        TaskService.addTask(new YearlyTask(taskName, typeTask, taskDescription, taskDate));
                        break label;
                    default:
                        System.out.println("Выберите от 1 до 5");
                        break;
                }
            } else {
                scanner.next();
            }
        }

    }

    //-разделены методы для обработки исключений, чтобы программа не вылетала
    //-тут проверяется правильно ли указан id, если введены буквы, срабатывает исключение
    public static int checkId(Scanner scanner) throws IncorrectArgumentException {
        int intId;
        try {
            intId = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IncorrectArgumentException("Некорректный ввод");
        }
        return intId;
    }

    //- добавление типа задачи. Необходимо указывать либо "Личная", либо "Рабочая", иначе задача не добавится
    public static Type typeTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Выберите тип задачи: ");
        System.out.println("Личная, Рабочая");
        String input = scanner.next();
        Type typeTask = (Objects.equals(input, "Личная")) ? Type.PERSONAL : (Objects.equals(input, "Рабочая")) ? Type.WORK : null;
        if (typeTask == null) {
            throw new IncorrectArgumentException("Некорректно указан тип задачи, добавьте задачу повторно");
        }
        return typeTask;
    }

    // - удаление задачи по введенному id. Осуществляется проверка на правильный ввод id, и выводится информация. Если задачи не обнаружено, появляется соответствующее оповещение
    public static void delDask(Scanner scanner) throws IncorrectArgumentException {
        int inputId = checkId(scanner);
        try {
            TaskService.removeTask(inputId);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    // - проверка корректности ввода даты при поиске задачи. Если дата введена некорректно, появляется соответствующее оповещение.
    // - также настроена сортировка по времени задачи за указанное число. Сверху вниз идут задачи по времени, независимо от номера id.
    public static void dateFormat(Scanner scanner) throws IncorrectArgumentException {
        LocalDate date;
        try {
            scanner.nextLine();
            String str = scanner.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(str, dtf);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Некорректно введена дата");
        }
        try {
            TaskService.getAllByDate(date);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }


}


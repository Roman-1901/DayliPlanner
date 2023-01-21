import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
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

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.println("Выберите повторяемость задачи: ");
        System.out.println("1.Однократная 2.Ежедневная 3.Еженедельная 4.Ежемесячная 5.Ежегодная");
        int replayTask = scanner.nextInt();
        if (replayTask < 1 || replayTask > 5) {
            System.out.println("Укажите от 1 до 5");
        }
        System.out.println("Выберите тип задачи: ");
        System.out.println("1.Личная 2.Рабочая ");
        int typeTask = scanner.nextInt();
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
    }



}
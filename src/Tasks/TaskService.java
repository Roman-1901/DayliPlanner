package Tasks;

import Exceptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    private static Set<Task> setTask = new HashSet<>();

    public static void addTask(Task task) {
        if (Objects.nonNull(task.getDateTime()) && Objects.nonNull(task.getType()) && !task.getTitle().isEmpty() && !task.getDescription().isEmpty()) {
            setTask.add(task);
        } else {
            System.out.println();
            System.out.println("Заполнены не все данные");
            System.out.println();
        }
    }

    public static boolean deleteTask(int idNum) {
        return setTask.removeIf(i -> i.getId() == idNum);
    }

    public static void removeTask(int idNum) throws TaskNotFoundException {
        if (deleteTask(idNum)) {
            System.out.println();
            System.out.println("Задача с номером " + idNum + " удалена");
            System.out.println();
        } else {
            throw new TaskNotFoundException("Данный id не найден");
        }
    }


    public static void getAllByDate(LocalDate date) throws TaskNotFoundException {
        Set<Task> temp;
        System.out.println("Запланированные задачи за " + date);
        temp = setTask.stream().filter(task -> task.appearsln(date)).collect(Collectors.toSet());
        if (!temp.isEmpty()) {
            temp.stream().sorted(Comparator.comparing(Task::getTime)).forEach(System.out::println);
        } else {
            throw new TaskNotFoundException("За текущий день задач не обнаружено");
        }
    }

}

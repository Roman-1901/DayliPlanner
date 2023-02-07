package Tasks;

import Exceptions.IncorrectArgumentException;
import Exceptions.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Task {
    private static int idGenerator;
    private String title;
    private Type type;
    private int id;
    private LocalDateTime dateTime;

    private String description;

    private static Set<Task> setTask = new HashSet<>();

    public Task(String title, Type type, String description, String dateTime){
        idGenerator += 1;
        this.title = title;
        this.type = type;
        this.id = idGenerator;
        this.description = description;
        try {
            setDateTime(dateTime);
        } catch (IncorrectArgumentException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalTime getTime() {
        return getDateTime().toLocalTime();
    }

    public void setDateTime(String dateTime) throws IncorrectArgumentException{
        boolean check = true;
        try {
            this.dateTime =  LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }catch (DateTimeParseException e) {
            check = false;
        }
        if (!check) {
            throw new IncorrectArgumentException("Некорректно указана дата, добавьте задачу повторно");
        }
    }

    public String getDescription() {
        return description;
    }

    public static void addTask(Task task) {
        if (Objects.nonNull(task.getDateTime()) && Objects.nonNull(task.type)) {
            setTask.add(task);
        }
    }

    public static boolean deleteTask(int idNum){
      return setTask.removeIf(i-> i.getId() == idNum);
    }

    public static void removeTask(int idNum) throws TaskNotFoundException {
            if (deleteTask(idNum)) {
                System.out.println();
                System.out.println("Задача с номером " + idNum + " удалена");
                System.out.println();
            }
            else {
                throw new TaskNotFoundException("Данный id не найден");
            }
        }

    public abstract boolean appearsln(LocalDate date);


    public static void showTasks(LocalDate date) throws TaskNotFoundException {
        Set<Task> temp;
        System.out.println("Запланированные задачи за " + date);
        temp = setTask.stream().filter(task -> task.appearsln(date)).collect(Collectors.toSet());
        if (!temp.isEmpty()) {
            temp.stream().sorted(Comparator.comparing(Task::getTime)).forEach(System.out::println);
        }
        else {
            throw new TaskNotFoundException("За текущий день задач не обнаружено");
        }
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return title.equals(task.title) && type == task.type && dateTime.equals(task.dateTime) && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, dateTime, description);
    }

    @Override
    public String toString() {
        return
                "id задачи: " + id + "\n" +
                "Тип задачи: " + type + "\n" +
                "Время задачи: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                "Наименование: " + title + "\n" +
                "Описание: " + description + "\n";
    }
}

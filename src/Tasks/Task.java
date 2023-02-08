package Tasks;

import Exceptions.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public abstract class Task {
    private static int idGenerator;
    private String title;
    private Type type;
    private int id;
    private LocalDateTime dateTime;

    private String description;

    private static Set<Task> setTask = new HashSet<>();
    private static Map<Integer, Task> mapTask = new HashMap<>();

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
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




    public abstract boolean appearsln(LocalDate date);






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

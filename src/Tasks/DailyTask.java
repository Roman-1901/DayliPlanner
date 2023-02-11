package Tasks;

import Exceptions.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DailyTask extends Task {

    public DailyTask(String title, Type type, String description, String dateTime) {
        super(title, type, description, dateTime);
    }

    public boolean appearsIn(LocalDate date) {
        return date.equals(getDateTime().toLocalDate()) || date.isAfter(getDateTime().toLocalDate());
    }




    @Override
    public String toString() {
        return
                "Ежедневная задача" + "\n" +
                "id задачи: " + getId() + "\n" +
                        "Тип задачи: " + getType() + "\n" +
                        "Время задачи: " + getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                        "Наименование: " + getTitle() + "\n" +
                        "Описание: " + getDescription() + "\n";
    }
}

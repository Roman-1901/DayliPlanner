package Tasks;

import Exceptions.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class OneTimeTask extends Task {

    public OneTimeTask(String title, Type type, String description, String dateTime) {
        super(title, type, description, dateTime);
    }

    public boolean appearsIn(LocalDate date) {

        return Objects.equals(getDateTime().toLocalDate(), date);
    }



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return
                "Однократная задача" + "\n" +
                        "id задачи: " + getId() + "\n" +
                        "Тип задачи: " + getType() + "\n" +
                        "Время задачи: " + getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                        "Наименование: " + getTitle() + "\n" +
                        "Описание: " + getDescription() + "\n";
    }
}

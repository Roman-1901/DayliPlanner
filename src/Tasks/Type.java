package Tasks;

public enum Type {
    PERSONAL(1), WORK(2);
    private final Integer taskType;

    Type(Integer taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return (taskType == 1) ? "Личная" : (taskType == 2) ? "Рабочая" : null;
    }
}

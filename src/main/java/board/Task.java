package board;

import java.time.LocalDate;

/**
 * Класс Задачи
 */
public class Task extends BasicItem {

    private TaskType type;

    public Task(LocalDate dateCreate, LocalDate dateStart, LocalDate dateComplete,
                Status status, String responsiblePerson, int completePercent,
                TaskType type) {
        super(dateCreate, dateStart, dateComplete, status, responsiblePerson, completePercent);
        this.type = type;
    }

    public LocalDate calculatePDZ () {
        LocalDate toDay = LocalDate.now();
        double k = getK();

        int difDays = toDay.getDayOfMonth() - getDateCreate().getDayOfMonth();

        double answer = 1/k * (100 - getCompletePercent()) * difDays / getCompletePercent();

        return toDay.plusDays((long) Math.floor(answer));
    }

    private double getK() {
        switch (type) {
            default:
            case BUG: return 0.8;
            case IMPROVEMENT: return 0.85;
            case FEATURE: return 0.9;
        }
    }
}

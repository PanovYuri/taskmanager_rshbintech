package board;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Класс Требования
 * tasksPool - словарь задач, ключ это ответственное лицо, которое по условию является уникальным
 */
public class UserStory extends BasicItem {

    private HashMap<String, Task> tasksPool = new HashMap<>();

    public UserStory(LocalDate dateCreate, LocalDate dateStart, LocalDate dateComplete, Status status, String responsiblePerson, int completePercent) {
        super(dateCreate, dateStart, dateComplete, status, responsiblePerson, completePercent);
    }

    public void addTask(Task task) {
        tasksPool.put(task.getResponsiblePerson(), task);
    }

    public LocalDate getMaxPDZ() {
        LocalDate maxPDZ = LocalDate.now();
        for (Task task: tasksPool.values()) {
            if (task.getCompletePercent() == 100) continue;
            LocalDate taskPDZ = task.calculatePDZ();
            if (maxPDZ.compareTo(taskPDZ) < 0) {
                maxPDZ = taskPDZ;
            }
        }
        return maxPDZ;
    }
}

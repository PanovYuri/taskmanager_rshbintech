package board;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Класс проекта
 */
public class Project extends BasicItem {

    private HashMap<String, UserStory> userStoryPool = new HashMap<>();

    public void addUserStory(UserStory userStory) {
        userStoryPool.put(userStory.getResponsiblePerson(), userStory);
    }

    public Project(LocalDate dateCreate, LocalDate dateStart, LocalDate dateComplete, Status status, String responsiblePerson, int completePercent) {
        super(dateCreate, dateStart, dateComplete, status, responsiblePerson, completePercent);
    }

    public LocalDate getMaxPDZ() {
        LocalDate maxPDZ = LocalDate.now();
        for (UserStory userStory: userStoryPool.values()) {
            LocalDate userStoryPDZ = userStory.getMaxPDZ();
            if (maxPDZ.compareTo(userStoryPDZ) < 0) {
                maxPDZ = userStoryPDZ;
            }
        }
        return maxPDZ;
    }

}

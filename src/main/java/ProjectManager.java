import board.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectManager {

    /**
     * Метод вычисляющий ПДЗ всех проектов
     * @param projects - массив проектов
     * @return - объект LocalDate ПДЗ проектов
     */
    public static LocalDate getPDZ(ArrayList<Project> projects) {
        LocalDate maxPDZ = LocalDate.now();
        for (Project project: projects) {
            LocalDate projectPDZ = project.getMaxPDZ();
            if (projectPDZ.compareTo(maxPDZ) > 0)
                maxPDZ = projectPDZ;
        }
        return maxPDZ;
    }
}

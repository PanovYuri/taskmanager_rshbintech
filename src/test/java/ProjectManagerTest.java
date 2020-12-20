import board.*;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectManagerTest {
    Date beforeDate;
    LocalDate actual;
    private final String FILE_NAME = "test_results.json";

    ArrayList<Project> projects;

    @Before
    public void beforeTest() {
        // test_results.json exists
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                JSONObject mainObj = new JSONObject();
                JSONArray array = new JSONArray();
                mainObj.put("TestResults", array);
                FileWriter writer = new FileWriter(file);
                writer.write(mainObj.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        projects = new ArrayList<>();

        // Init tasks
        Task task1 = new Task(
                LocalDate.of(2020, 9, 19),
                LocalDate.of(2020, 11, 19),
                LocalDate.of(2020, 12, 20),
                Status.NEW,
                "Михаил Теряпкин",
                30,
                TaskType.BUG);
        Task task2 = new Task(
                LocalDate.of(2020, 9, 20),
                LocalDate.of(2020, 12, 2),
                LocalDate.of(2020, 12, 20),
                Status.NEW,
                "Михаил Теряпкин",
                45,
                TaskType.BUG);
        Task task3 = new Task(
                LocalDate.of(2020, 9, 20),
                LocalDate.of(2020, 12, 3),
                LocalDate.of(2020, 12, 20),
                Status.NEW,
                "Михаил Теряпкин",
                60,
                TaskType.BUG);
        Task task4 = new Task(
                LocalDate.of(2020, 9, 23),
                LocalDate.of(2020, 11, 5),
                LocalDate.of(2020, 12, 20),
                Status.NEW,
                "Михаил Теряпкин",
                50,
                TaskType.BUG);
        Task task5 = new Task(
                LocalDate.of(2020, 9, 23),
                LocalDate.of(2020, 11, 6),
                LocalDate.of(2020, 12, 20),
                Status.NEW,
                "Михаил Теряпкин",
                50,
                TaskType.BUG);

        // Init user stories
        UserStory userStory1 = new UserStory(
                LocalDate.of(2020, 9, 19),
                LocalDate.of(2020, 10, 1),
                LocalDate.of(2020, 12, 29),
                Status.NEW,
                "Михаил Теряпкин",
                8);
        userStory1.addTask(task1);
        userStory1.addTask(task2);
        userStory1.addTask(task3);
        UserStory userStory2 = new UserStory(
                LocalDate.of(2020, 9, 19),
                LocalDate.of(2020, 10, 2),
                LocalDate.of(2020, 12, 30),
                Status.NEW,
                "Михаил Теряпкин",
                25);
        userStory2.addTask(task4);
        userStory2.addTask(task5);

        Project project1 = new Project(
                LocalDate.of(2020, 6, 18),
                LocalDate.of(2020, 9, 19),
                LocalDate.of(2020, 12, 31),
                Status.NEW,
                "Михаил Теряпкин",
                2
        );
        project1.addUserStory(userStory1);
        project1.addUserStory(userStory2);
        projects.add(project1);
        System.out.println("Тестирование ");
    }

    @Test
    public void checkMainPDZ() {
        beforeDate = new Date();
        actual = ProjectManager.getPDZ(projects);
        LocalDate expected = LocalDate.of(2020, 12, 21);

        Assert.assertEquals(actual, expected);
    }

    @After
    public void saveTest() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date afterDate = new Date();
        try {
            JSONObject mainObj = new JSONObject(new String(Files.readAllBytes(Paths.get(FILE_NAME))));
            JSONArray arr = (JSONArray) mainObj.get("TestResults");
            JSONObject answer = new JSONObject();

            answer.put("TestName", "Тестирование ProjectManager");
            answer.put("EntityCount", 8);
            answer.put("StartDateTime", df.format(beforeDate));
            answer.put("EndDateTime", df.format(afterDate));
            answer.put("ReturnValue", actual);

            arr.put(answer);

            File file = new File(FILE_NAME);
            file.createNewFile();
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(mainObj.toString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

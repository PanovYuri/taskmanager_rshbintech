import board.Status;
import board.Task;
import board.TaskType;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskTest {

    ArrayList<Task> arrayList;

    @Test
    public void setUp() {
        System.out.println("Code executes before each test method");
        Task task1 = new Task(
                LocalDate.of(2020, 12, 17),
                LocalDate.of(2020, 12, 19),
                LocalDate.of(2020, 12, 28),
                Status.NEW,
                "Михаил Теряпкин",
                1,
                TaskType.BUG);
        Task task2 = new Task(
                LocalDate.of(2020, 11, 17),
                LocalDate.of(2020, 11, 29),
                LocalDate.of(2020, 12, 30),
                Status.NEW,
                "Михаил Теряпкин",
                10,
                TaskType.FEATURE);
        Task task3 = new Task(
                LocalDate.of(2020, 10, 17),
                LocalDate.of(2020, 11, 19),
                LocalDate.of(2020, 12, 25),
                Status.NEW,
                "Михаил Теряпкин",
                20,
                TaskType.IMPROVEMENT);
        ArrayList<LocalDate> expected = new ArrayList<LocalDate>(){{
            add(task1.calculatePDZ());
            add(task2.calculatePDZ());
            add(task3.calculatePDZ());
        }};

        ArrayList<LocalDate> actual = new ArrayList<LocalDate>(){{
            add(LocalDate.of(2022, 4, 30));
            add(LocalDate.of(2021, 1, 30));
            add(LocalDate.of(2021, 1, 8));
        }};

        Assert.assertEquals(expected, actual);
    }

}

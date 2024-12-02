import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StudentTest {

    @Test
    public void testWire0() throws Utilities.BoardConstructionException {
        ArrayList<Endpoints> goals1 = new ArrayList<>();
        ArrayList<Integer[]> obstacles1 = new ArrayList<>();
        Board b1 = Utilities.buildBoard(goals1, obstacles1, "test/inputs/ex1.in");
        long startTime = System.nanoTime();
        Routing.findPaths(b1, goals1);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + duration / 1_000_000);
    }

}

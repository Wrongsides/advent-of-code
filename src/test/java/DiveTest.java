import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiveTest {

    @Test
    void dive_following_input_course_has_position_900() {
        Dive dive = new Dive();
        String[] input = {"forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2"};

        dive.inputCourse(Arrays.stream(input));

        assertEquals(900, dive.position());
    }

    @Test
    void dive_following_input_course_has_position_1727785422() {
        Dive dive = new Dive();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dive-input.txt");
        Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();

        dive.inputCourse(lines);

        assertEquals(1727785422, dive.position());
    }
}

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonarSweepTest {

    @Test
    void sample_measures_increase_7_times() {
        SonarSweep sonarSweep = new SonarSweep();
        IntStream measures = Arrays.stream(new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263});

        int depthIncreases = sonarSweep.depthIncreases(measures);

        assertEquals(7, depthIncreases);
    }

    @Test
    void input_text_returns_number_of_depth_increases() {
        SonarSweep sonarSweep = new SonarSweep();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("input.txt");
        IntStream measures = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().mapToInt(Integer::parseInt);

        int depthIncreases = sonarSweep.depthIncreases(measures);

        assertEquals(1548, depthIncreases);
    }

    @Test
    void simple_measures_return_5_window_sums_larger_than_the_previous_sum() {
        SonarSweep sonarSweep = new SonarSweep();
        IntStream measures = Arrays.stream(new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263});

        int increases = sonarSweep.slidingWindowIncreases(3, measures);

        assertEquals(5, increases);
    }

    @Test
    void input_text_returns_number_of_window_sums_larger_than_the_previous_sum() {
        SonarSweep sonarSweep = new SonarSweep();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("input.txt");
        IntStream measures = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().mapToInt(Integer::parseInt);

        int increases = sonarSweep.slidingWindowIncreases(3, measures);

        assertEquals(1589, increases);
    }
}

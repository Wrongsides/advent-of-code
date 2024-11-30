import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryDiagnosticTest {

    @Test
    void power_consumption_from_input_is_198() {
        String[] input = {"00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"};
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(input);

        int powerConsumption = binaryDiagnostic.powerConsumption();

        assertEquals(198, powerConsumption);
    }

    @Test
    void power_consumption_from_input_is_4147524() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("binary-diagnostic-input.txt");
        Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(lines.toArray(String[]::new));

        int powerConsumption = binaryDiagnostic.powerConsumption();

        assertEquals(4147524, powerConsumption);
    }

    @Test
    void oxygen_generator_rating_from_input_is_23() {
        String[] input = {"00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"};
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(input);

        int oxygenGeneratorRating = binaryDiagnostic.oxygenGeneratorRating();

        assertEquals(23, oxygenGeneratorRating);
    }

    @Test
    void oxygen_generator_rating_from_input_is_10() {
        String[] input = {"00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"};
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(input);

        int co2ScrubberRating = binaryDiagnostic.co2ScrubberRating();

        assertEquals(10, co2ScrubberRating);
    }

    @Test
    void life_support_rating_from_input_is_230() {
        String[] input = {"00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"};
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(input);

        int lifeSupportRating = binaryDiagnostic.lifeSupportRating();

        assertEquals(230, lifeSupportRating);
    }

    @Test
    void life_support_rating_from_input_is_3570354() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("binary-diagnostic-input.txt");
        Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(lines.toArray(String[]::new));

        int lifeSupportRating = binaryDiagnostic.lifeSupportRating();

        assertEquals(3570354, lifeSupportRating);
    }
}

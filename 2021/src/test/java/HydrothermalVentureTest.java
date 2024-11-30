import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrothermalVentureTest {

    @Test
    void coordinates_which_form_a_diaganal_are_removed_from_hydrothermal_lines() {
        HydrothermalVenture hydrothermalVenture = new HydrothermalVenture();

        Map<HydrothermalVenture.Coordinate, HydrothermalVenture.Coordinate> lines = hydrothermalVenture.filterOutDiaganals(testData());

        assertEquals(6, lines.size());
    }

    @Test
    void output_number_of_overlapping_straight_lines_from_test_coordinates() {
        HydrothermalVenture hydrothermalVenture = new HydrothermalVenture();

        int overlappingLines = hydrothermalVenture.overlappingStraightLines(testData(), 10);

        assertEquals(5, overlappingLines);
    }

    @Test
    void output_number_of_overlapping_straight_lines_from_input_file_coordinates() {
        HydrothermalVenture hydrothermalVenture = new HydrothermalVenture();

        int overlappingLines = hydrothermalVenture.overlappingStraightLines(testDataFromFile(), 1000);

        assertEquals(6856, overlappingLines);
    }

    @Test
    void output_number_of_overlapping_lines_from_test_coordinates() {
        HydrothermalVenture hydrothermalVenture = new HydrothermalVenture();

        int overlappingLines = hydrothermalVenture.overlappingLines(testData(), 10);

        assertEquals(12, overlappingLines);
    }

    @Test
    void output_number_of_overlapping_lines_from_input_file_coordinates() {
        HydrothermalVenture hydrothermalVenture = new HydrothermalVenture();

        int overlappingLines = hydrothermalVenture.overlappingLines(testDataFromFile(), 1000);

        assertEquals(20666, overlappingLines);
    }

    private Map<HydrothermalVenture.Coordinate, HydrothermalVenture.Coordinate> testDataFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hydrothermal-venture-input.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            Stream<String> lines = br.lines();
            return lines.map(line -> {
                String[] coordinates = line.split(" -> ");
                String[] from = coordinates[0].split(",");
                String[] to = coordinates[1].split(",");
                return new AbstractMap.SimpleEntry<>(
                        new HydrothermalVenture.Coordinate(Integer.parseInt(from[0]), Integer.parseInt(from[1])),
                        new HydrothermalVenture.Coordinate(Integer.parseInt(to[0]), Integer.parseInt(to[1]))
                );
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (IOException e) {
            throw new RuntimeException("bang!");
        }
    }

    private Map<HydrothermalVenture.Coordinate, HydrothermalVenture.Coordinate> testData() {
        Map<HydrothermalVenture.Coordinate, HydrothermalVenture.Coordinate> coordinates = new HashMap<>();
        coordinates.put(new HydrothermalVenture.Coordinate(0, 9), new HydrothermalVenture.Coordinate(5, 9));
        coordinates.put(new HydrothermalVenture.Coordinate(8, 0), new HydrothermalVenture.Coordinate(0, 8));
        coordinates.put(new HydrothermalVenture.Coordinate(9, 4), new HydrothermalVenture.Coordinate(3, 4));
        coordinates.put(new HydrothermalVenture.Coordinate(2, 2), new HydrothermalVenture.Coordinate(2, 1));
        coordinates.put(new HydrothermalVenture.Coordinate(7, 0), new HydrothermalVenture.Coordinate(7, 4));
        coordinates.put(new HydrothermalVenture.Coordinate(6, 4), new HydrothermalVenture.Coordinate(2, 0));
        coordinates.put(new HydrothermalVenture.Coordinate(0, 9), new HydrothermalVenture.Coordinate(2, 9));
        coordinates.put(new HydrothermalVenture.Coordinate(3, 4), new HydrothermalVenture.Coordinate(1, 4));
        coordinates.put(new HydrothermalVenture.Coordinate(0, 0), new HydrothermalVenture.Coordinate(8, 8));
        coordinates.put(new HydrothermalVenture.Coordinate(5, 5), new HydrothermalVenture.Coordinate(8, 2));
        return coordinates;
    }
}

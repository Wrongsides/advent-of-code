package co.uk.wrongsides;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuardGallivantTest {

    @Test
    void reads_input() {
        String input = """
                .#..
                ...#
                .^..""".stripIndent();

        GuardGallivant guardGallivant = new GuardGallivant(input);

        assertNotNull(guardGallivant);
    }

    @Test
    void calculate_distinct_positions_before_exiting_the_map() {
        String input = """
                .#..
                ...#
                .^..""".stripIndent();
        GuardGallivant guardGallivant = new GuardGallivant(input);

        long positionCount = guardGallivant.getPositionCount();

        assertEquals(4, positionCount);
    }

    @Test
    void calculate_distinct_positions_for_sample_input() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...""".stripIndent();
        GuardGallivant guardGallivant = new GuardGallivant(input);

        long positionCount = guardGallivant.getPositionCount();

        assertEquals(41, positionCount);
    }

    @Test
    void calculate_distinct_positions_from_file() throws IOException {
        String input = GuardGallivant.readFile("input");
        GuardGallivant guardGallivant = new GuardGallivant(input);

        long positionCount = guardGallivant.getPositionCount();

        assertEquals(5461, positionCount);
    }

    @Test
    void returns_empty_when_guard_enters_loop() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#.#^.....
                ........#.
                #.........
                ......#...""".stripIndent();
        GuardGallivant guardGallivant = new GuardGallivant(input);

        Set<Step> steps = guardGallivant.findPath(
                guardGallivant.getStart(),
                guardGallivant.getObstacles()
        );

        assertTrue(steps.isEmpty());
    }

    @Test
    void returns_path_when_guard_does_not_loop() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...""".stripIndent();
        GuardGallivant guardGallivant = new GuardGallivant(input);

        Set<Step> steps = guardGallivant.findPath(
                guardGallivant.getStart(),
                guardGallivant.getObstacles()
        );

        assertFalse(steps.isEmpty());
    }

    @Test
    void finds_6_loops_from_example() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...""".stripIndent();
        GuardGallivant guardGallivant = new GuardGallivant(input);

        long positionCount = guardGallivant.getLoops();

        assertEquals(6, positionCount);
    }

    @Test
    void calculate_number_of_loops_from_file() throws IOException {
        String input = GuardGallivant.readFile("input");
        GuardGallivant guardGallivant = new GuardGallivant(input);

        long loops = guardGallivant.getLoops();

        assertEquals(1836, loops);
    }
}

package co.uk.wrongsides;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GuardGallivantTest {

    @Test
    void reads_input() {
        GuardGallivant guardGallivant = new GuardGallivant();
        String input = """
                .#..
                ...#
                .^..""".stripIndent();

        guardGallivant.read(input);

        assertNotNull(guardGallivant.getMap());
    }

    @Test
    void can_get_the_obsticle_coordinates() {
        GuardGallivant guardGallivant = new GuardGallivant();
        String input = """
                .#..
                ...#
                .^..""".stripIndent();

        guardGallivant.read(input);

        assertEquals(Set.of(Map.entry(1, 0), Map.entry(3, 1)), guardGallivant.getObsticles());
    }

    @Test
    void calculate_distinct_positions_before_exiting_the_map() {
        GuardGallivant guardGallivant = new GuardGallivant();
        String input = """
                .#..
                ...#
                .^..""".stripIndent();

        guardGallivant.read(input);

        assertEquals(4, guardGallivant.getPositionCount());
    }

    @Test
    void calculate_distinct_positions_for_sample_input() {
        GuardGallivant guardGallivant = new GuardGallivant();
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

        guardGallivant.read(input);

        assertEquals(41, guardGallivant.getPositionCount());
    }

    @Test
    void calculate_distinct_positions_from_file() throws IOException {
        GuardGallivant guardGallivant = new GuardGallivant();
        String input = guardGallivant.readFile("input");

        guardGallivant.read(input);

        assertEquals(5461, guardGallivant.getPositionCount());
    }
}

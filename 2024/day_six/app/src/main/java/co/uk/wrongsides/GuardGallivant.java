package co.uk.wrongsides;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GuardGallivant {

    private final Coordinate boundary;
    private final Set<Coordinate> obstacles;
    private Step start;

    public GuardGallivant(String input) {
        this.boundary = findBoundary(input);
        this.obstacles = findObstacles(input);
    }

    public Step getStart() {
        return start;
    }

    public Set<Coordinate> getObstacles() {
        return obstacles;
    }

    public long getPositionCount() {
        return findPath(start, obstacles).stream().map(Step::coordinate).distinct().count();
    }

    public long getLoops() {
        long loops = 0;
        Set<Coordinate> pathCoordinates = findPath(start, obstacles).stream()
                .map(Step::coordinate)
                .collect(Collectors.toSet());

        for (Coordinate obstacle : pathCoordinates) {
            Set<Coordinate> extraObstacles = new HashSet<>(obstacles);
            extraObstacles.add(obstacle);

            if (notStart(obstacle) && inMap(obstacle) && extraObstacles.size() > obstacles.size()) {
                if (findPath(start, extraObstacles).isEmpty()) {
                    loops++;
                }
            }
        }
        return loops;
    }

    public Set<Step> findPath(Step start, Set<Coordinate> obstacles) {
        Set<Step> steps = new HashSet<>();
        Step location = start;

        while (inMap(location.coordinate())) {
            steps.add(location);
            char direction = location.direction();
            if (obstacles.contains(move(location.coordinate(), direction))) {
                direction = turn(direction);
            }
            if (obstacles.contains(move(location.coordinate(), direction))) {
                direction = turn(direction);
            }
            Coordinate next = move(location.coordinate(), direction);
            location = new Step(next, direction);
            if (steps.contains(location)) {
                return Set.of();
            }
        }
        return steps;
    }

    private boolean notStart(Coordinate step) {
        return !step.equals(start.coordinate());
    }

    private boolean inMap(Coordinate coordinate) {
        return coordinate.x() <= boundary.x() && coordinate.x() >= 0
                && coordinate.y() <= boundary.y() && coordinate.y() >= 0;
    }

    private Set<Coordinate> findObstacles(String map) {
        int x = 0;
        int y = 0;
        Set<Coordinate> obstacles = new HashSet<>();
        for (char symbol : map.toCharArray()) {
            switch (symbol) {
                case '.' -> x++;
                case '\n' -> {
                    y++;
                    x = 0;
                }
                case '#' -> {
                    obstacles.add(new Coordinate(x, y));
                    x++;
                }
                case '^', 'v', '<', '>' -> {
                    start = new Step(new Coordinate(x, y), symbol);
                    x++;
                }
                default -> throw new RuntimeException("Unknown Symbol");
            }
        }
        return obstacles;
    }

    private Coordinate findBoundary(String map) {
        String[] rows = map.split("\n");
        char[] columns = rows[0].toCharArray();
        return new Coordinate(columns.length - 1, rows.length - 1);
    }

    private char turn(char direction) {
        return switch (direction) {
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            case '<' -> '^';
            default -> throw new RuntimeException("Unknown direction");
        };
    }

    private Coordinate move(Coordinate location, char direction) {
        return switch (direction) {
            case '^' -> new Coordinate(location.x(), location.y() - 1);
            case '>' -> new Coordinate(location.x() + 1, location.y());
            case 'v' -> new Coordinate(location.x(), location.y() + 1);
            case '<' -> new Coordinate(location.x() - 1, location.y());
            default -> throw new RuntimeException("Unknown direction");
        };
    }

    public static String readFile(String filename) throws IOException {
        ClassLoader classLoader = GuardGallivant.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        return readFromInputStream(inputStream);
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}

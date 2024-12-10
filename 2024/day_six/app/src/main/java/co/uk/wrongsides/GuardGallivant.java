package co.uk.wrongsides;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GuardGallivant {

    private char direction;
    private String map;
    private Map.Entry<Integer, Integer> start;
    private Map.Entry<Integer, Integer> boundry;
    private Set<Map.Entry<Integer, Integer>> obsticles = new HashSet<>();

    public void read(String input) {
        this.map = input;
        this.obsticles = findObsticles();
    }

    public String getMap() {
        return map;
    }

    public Set<Map.Entry<Integer, Integer>> getObsticles() {
        return obsticles;
    }

    private Set<Map.Entry<Integer, Integer>> findObsticles() {
        Integer x = 0;
        Integer y = 0;
        Integer xBoundry = 0;
        Set<Map.Entry<Integer, Integer>> obsticles = new HashSet<>();

        for (char symbol : getMap().toCharArray()) {
            switch (symbol) {
                case '.' -> x++;
                case '\n' -> {
                    y++;
                    xBoundry = x;
                    x = 0;
                }
                case '#' -> {
                    obsticles.add(Map.entry(x, y));
                    x++;
                }
                case '^', 'v', '<', '>' -> {
                    start = Map.entry(x, y);
                    direction = symbol;
                    x++;
                }
                default -> throw new RuntimeException("Unknown Symbol");
            }
        }
        boundry = Map.entry(xBoundry, y);
        return obsticles;
    }

    public Integer getPositionCount() {
        Set<Map.Entry<Integer, Integer>> positions = new HashSet<>();
        Map.Entry<Integer, Integer> location = start;

        while (location.getKey() <= boundry.getKey() && location.getKey() >= 0
                && location.getValue() <= boundry.getValue() && location.getValue() >= 0) {
            positions.add(location);
            if (obsticles.contains(move(direction, location))) {
                direction = turn(direction);
            }
            location = move(direction, location);
        }
        return positions.size();
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

    private Map.Entry<Integer, Integer> move(char direction, Map.Entry<Integer, Integer> location) {
        return switch (direction) {
            case '^' -> Map.entry(location.getKey(), location.getValue() - 1);
            case '>' -> Map.entry(location.getKey() + 1, location.getValue());
            case 'v' -> Map.entry(location.getKey(), location.getValue() + 1);
            case '<' -> Map.entry(location.getKey() - 1, location.getValue());
            default -> throw new RuntimeException("Unknown direction");
        };
    }

    public String readFile(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        return readFromInputStream(inputStream);
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
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

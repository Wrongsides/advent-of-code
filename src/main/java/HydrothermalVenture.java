import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HydrothermalVenture {

    public Integer overlappingStraightLines(Map<Coordinate, Coordinate> lines, int grid) {
        return overlappingLines(filterOutDiaganals(lines), grid);
    }

    public Integer overlappingLines(Map<Coordinate, Coordinate> lines, int grid) {
        int[][] diagram = new int[grid][grid];
        for (Map.Entry<Coordinate, Coordinate> line : lines.entrySet()) {
            if (line.getKey().getX() == line.getValue().getX()) {
                horizontal(grid, diagram, line);
            } else if (line.getKey().getY() == line.getValue().getY()) {
                vertical(grid, diagram, line);
            } else {
                diaganal(diagram, line);
            }
        }
        int overlaps = 0;
        for (int[] line : diagram) {
            overlaps += Arrays.stream(line).filter(i -> i >= 2).count();
        }
        return overlaps;
    }

    private void horizontal(int grid, int[][] diagram, Map.Entry<Coordinate, Coordinate> line) {
        int from = line.getKey().getY();
        int to = line.getValue().getY();
        for (int i = 0; i < grid; i++) {
            if (i >= from && i <= to || i >= to && i <= from) {
                diagram[i][line.getKey().getX()]++;
            }
        }
    }

    private void vertical(int grid, int[][] diagram, Map.Entry<Coordinate, Coordinate> line) {
        int from = line.getKey().getX();
        int to = line.getValue().getX();
        for (int i = 0; i < grid; i++) {
            if (i >= from && i <= to || i >= to && i <= from) {
                diagram[line.getKey().getY()][i]++;
            }
        }
    }

    private void diaganal(int[][] diagram, Map.Entry<Coordinate, Coordinate> line) {
        Coordinate from = line.getKey();
        Coordinate to = line.getValue();
        int x = from.getX();
        int y = from.getY();
        while (!(to.getX() == x && to.getY() == y)) {
            diagram[y][x]++;
            if (x < to.getX()) {
                x++;
            } else if (x > to.getX()) {
                x--;
            }
            if (y < to.getY()) {
                y++;
            } else if (y > to.getY()) {
                y--;
            }
        }
        diagram[y][x]++;
    }

    public Map<Coordinate, Coordinate> filterOutDiaganals(Map<Coordinate, Coordinate> lines) {
        return lines.entrySet().stream()
                .filter(line -> line.getKey().getX() == line.getValue().getX() || line.getKey().getY() == line.getValue().getY())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

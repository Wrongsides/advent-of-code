import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BinaryDiagnostic {

    private final String[] report;
    private final Map<Integer, Integer> mostCommonBits;

    public BinaryDiagnostic(String[] report) {
        this.report = report;
        this.mostCommonBits = mostCommonBits(report);
    }

    public int powerConsumption() {
        return getRate(b -> b) * getRate(b -> b == 1 ? 0 : 1);
    }

    private Integer getRate(Function<Integer, Integer> rateMapping) {
        return mostCommonBits.values().stream()
                .map(rateMapping)
                .map(String::valueOf)
                .reduce((b1, b2) -> b1 + b2)
                .map(g -> Integer.parseInt(g, 2))
                .orElseThrow();
    }

    public int lifeSupportRating() {
        return oxygenGeneratorRating() * co2ScrubberRating();
    }

    private List<String> filterOxygenBitCriteria(List<String> results, int mostCommonBit, int pointer) {
        return results.stream()
                .filter(row -> Character.getNumericValue(row.charAt(pointer)) == mostCommonBit)
                .collect(Collectors.toList());
    }

    public int oxygenGeneratorRating() {
        List<String> results = Arrays.asList(report);
        int columns = results.get(0).length();
        for (int column = 0; column < columns; column++) {
            int mostCommonBit = mostCommonBit(column, results);
            results = filterOxygenBitCriteria(results, mostCommonBit, column);
            if (results.size() == 1) {
                break;
            }
        }
        return Integer.parseInt(results.get(0), 2);
    }

    private List<String> filterCo2BitCriteria(List<String> results, int mostCommonBit, int pointer) {
        return results.stream()
                .filter(row -> Character.getNumericValue(row.charAt(pointer)) != mostCommonBit)
                .collect(Collectors.toList());
    }

    public int co2ScrubberRating() {
        List<String> results = Arrays.asList(report);
        int columns = results.get(0).length();
        for (int column = 0; column < columns; column++) {
            int mostCommonBit = mostCommonBit(column, results);
            results = filterCo2BitCriteria(results, mostCommonBit, column);
            if (results.size() == 1) {
                break;
            }
        }
        return Integer.parseInt(results.get(0), 2);
    }

    private int mostCommonBit(int column, List<String> report) {
        Integer columnSum = report.stream()
                .map(row -> Character.getNumericValue(row.charAt(column)))
                .reduce(Integer::sum)
                .orElseThrow();
        return columnSum >= (int) Math.ceil(report.size() / 2.0) ? 1 : 0;
    }

    private Map<Integer, Integer> mostCommonBits(String[] report) {
        Map<Integer, Integer> mostCommonBits = new HashMap<>();
        int columns = report[0].length();
        for (int column = 0; column < columns; column++) {
            mostCommonBits.put(column, mostCommonBit(column, Arrays.asList(report)));
        }
        return mostCommonBits;
    }
}

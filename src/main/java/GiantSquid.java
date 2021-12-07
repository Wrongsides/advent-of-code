import java.util.*;

public class GiantSquid {

    private final Queue<Integer> numbers;
    private final List<int[][]> boards;
    private final Stack<Integer> winningScores;
    private Integer currentNumber;

    public GiantSquid(Queue<Integer> numbers, List<int[][]> boards) {
        this.numbers = numbers;
        this.boards = boards;
        this.winningScores = new Stack<>();
    }

    public void drawNumber() {
        currentNumber = numbers.poll();
        boards.forEach(this::markBoard);
    }

    public Optional<Integer> lastWinner() {
        List<int[][]> toRemove = new ArrayList<>();
        for (int[][] board : boards) {
            if (isWinner(board) != null) {
                winningScores.add(score(board) * currentNumber);
                toRemove.add(board);
            }
        }
        boards.removeAll(toRemove);
        if (numbers.isEmpty()) {
            return Optional.ofNullable(winningScores.peek());
        }
        return Optional.empty();
    }

    public Optional<Integer> firstWinner() {
        for (int[][] board : boards) {
            if (isWinner(board) != null) {
                return Optional.of(score(board) * currentNumber);
            }
        }
        return Optional.empty();
    }

    private Integer score(int[][] board) {
        return Arrays.stream(board)
                .flatMap(n -> Arrays.stream(n).boxed().reduce(Integer::sum).stream())
                .reduce(Integer::sum)
                .orElse(0);
    }

    private int[][] isWinner(int[][] board) {
        for (int[] row : board) {
            int unmarked = Arrays.stream(row).reduce(Integer::sum).orElse(0);
            if (unmarked == 0) {
                return board;
            }
        }
        for (int column = 0; column < board[0].length; column++) {
            int unmarked = 0;
            for (int[] row : board) {
                unmarked += row[column];
            }
            if (unmarked == 0) {
                return board;
            }
        }
        return null;
    }

    private void markBoard(int[][] board) {
        for (int[] row : board) {
            for (int column = 0; column < row.length; column++) {
                if (row[column] == currentNumber) {
                    row[column] = 0;
                }
            }
        }
    }
}

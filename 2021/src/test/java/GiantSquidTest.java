import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GiantSquidTest {

    @Test
    void third_board_wins_because_it_has_at_least_one_complete_row_of_marked_numbers() {
        GiantSquid giantSquid = new GiantSquid(testNumbers(), testBoards());
        while (giantSquid.firstWinner().isEmpty()) {
            giantSquid.drawNumber();
        }

        assertEquals(4512, giantSquid.firstWinner().get());
    }

    @Test
    void find_winning_board_from_input() {
        GiantSquid giantSquid = new GiantSquid(testNumbersFromFile(), testBoardsFromFile());
        while (giantSquid.firstWinner().isEmpty()) {
            giantSquid.drawNumber();
        }

        assertEquals(71708, giantSquid.firstWinner().get());
    }

    @Test
    void finds_last_board_to_win() {
        GiantSquid giantSquid = new GiantSquid(testNumbersFromFile(), testBoardsFromFile());
        while (giantSquid.lastWinner().isEmpty()) {
            giantSquid.drawNumber();
            giantSquid.lastWinner();
        }

        assertEquals(34726, giantSquid.lastWinner().get());
    }

    private List<int[][]> testBoardsFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("giant-squid-input.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] lines = br.lines().skip(2).toArray(String[]::new);
            List<int[][]> boards = new ArrayList<>();
            int[][] board = new int[5][5];
            int row = 0;
            for (String line : lines) {
                if (line.isBlank()) {
                    boards.add(board);
                    board = new int[5][5];
                    row = 0;
                } else {
                    board[row] = Arrays.stream(line.split("\\s+"))
                            .filter(not(String::isBlank))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    row++;
                }
            }
            return boards;
        } catch (IOException e) {
            throw new RuntimeException("bang!");
        }
    }

    private Queue<Integer> testNumbersFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("giant-squid-input.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] lines = br.lines().toArray(String[]::new);
            return Arrays.stream(lines[0].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedBlockingQueue<Integer>::new));
        } catch (IOException e) {
            throw new RuntimeException("bang!");
        }
    }

    private Queue<Integer> testNumbers() {
        Integer[] numbers = new Integer[]{7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1};
        return Arrays.stream(numbers).collect(Collectors.toCollection(LinkedBlockingQueue<Integer>::new));
    }

    private List<int[][]> testBoards() {
        List<int[][]> boards = new ArrayList<>();
        int[][] board1 = new int[5][5];
        board1[0] = new int[]{22, 13, 17, 11, 0};
        board1[1] = new int[]{8, 2, 23, 4, 24};
        board1[2] = new int[]{21, 9, 14, 16, 7};
        board1[3] = new int[]{6, 10, 3, 18, 5};
        board1[4] = new int[]{1, 12, 20, 15, 19};
        int[][] board2 = new int[5][5];
        board2[0] = new int[]{3, 15, 0, 2, 22};
        board2[1] = new int[]{9, 18, 13, 17, 5};
        board2[2] = new int[]{19, 8, 7, 25, 23};
        board2[3] = new int[]{20, 11, 10, 24, 4};
        board2[4] = new int[]{14, 21, 16, 12, 6};
        int[][] board3 = new int[5][5];
        board3[0] = new int[]{14, 21, 17, 24, 4};
        board3[1] = new int[]{10, 16, 15, 9, 19};
        board3[2] = new int[]{18, 8, 23, 26, 20};
        board3[3] = new int[]{22, 11, 13, 6, 5};
        board3[4] = new int[]{2, 0, 12, 3, 7};

        boards.add(board1);
        boards.add(board2);
        boards.add(board3);
        return boards;
    }
}

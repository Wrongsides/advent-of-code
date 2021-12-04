import java.util.stream.Stream;

public class Dive {

    private int horizontal;
    private int depth;
    private int aim;

    public void inputCourse(Stream<String> commands) {
        commands.forEach(c -> {
            if (c.startsWith("up")) {
                up(Integer.parseInt(c.split(" ")[1]));
            } else if (c.startsWith("down")) {
                down(Integer.parseInt(c.split(" ")[1]));
            } else if (c.startsWith("forward")) {
                forward(Integer.parseInt(c.split(" ")[1]));
            } else {
                throw new RuntimeException("Unknown command!");
            }
        });
    }

    public int position() {
        return horizontal * depth;
    }

    private void up(Integer units) {
        aim = aim - units;
    }

    private void down(Integer units) {
        aim = aim + units;
    }

    private void forward(Integer units) {
        horizontal = horizontal + units;
        depth = depth + (aim * units);
    }
}

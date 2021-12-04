import com.codepoetics.protonpack.StreamUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SonarSweep {

    public int slidingWindowIncreases(int windowSize, IntStream measures) {
        AtomicInteger count = new AtomicInteger();
        StreamUtils.windowed(measures.boxed(), windowSize, 1).reduce((w1, w2) -> {
            Integer w1sum = w1.stream().reduce(Integer::sum).orElse(0);
            Integer w2sum = w2.stream().reduce(Integer::sum).orElse(0);
            if (w2sum > w1sum) {
                count.getAndIncrement();
            }
            return w2;
        });
        return count.get();
    }

    public int depthIncreases(IntStream measures) {
        AtomicInteger count = new AtomicInteger();
        measures.reduce((m1, m2) -> {
            if (m2 > m1) {
                count.getAndIncrement();
            }
            return m2;
        });
        return count.get();
    }
}

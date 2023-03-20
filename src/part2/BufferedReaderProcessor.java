package part2;

import java.io.BufferedReader;

@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws Exception;
}

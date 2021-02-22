package zapisi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 */
public class RecordCutter {

    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Paths.get("C:\\Users\\Robert Pavliš\\Desktop\\data_small.csv"));
        Stream<String> stream = lines.
                map(line -> new TaxiRideRecord(line)).limit(10000).map(record -> record.toString());
        
        Files.write(Paths.get("C:\\Users\\Robert Pavliš\\Desktop\\data.csv"), (Iterable<String>)stream::iterator);
    }
}

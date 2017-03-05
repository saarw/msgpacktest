import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Decodes a message pack file expecting to consist of something that can be deserialized to an Outer.
 */
public class MsgPackReader {

    public static void main(String[] args) {
        Path path = new File(args[0]).toPath();
        decodeMessagePackFile(path);
    }

    static void decodeMessagePackFile(Path path) {
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        try {
            byte[] buf = Files.readAllBytes(path);
            try {
                Outer val = mapper.readValue(buf, Outer.class);
                System.out.println("OK, got: " + val);
            } catch (JsonProcessingException e) {
                System.out.println("Error, got message with structure: " + mapper.readTree(buf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Outer {
        public List<Inner> inners;

        @Override
        public String toString() {
            return "Outer{" +
                    "inners=" + inners +
                    '}';
        }
    }

    public static class Inner {
        public String value;

        @Override
        public String toString() {
            return "Inner{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}

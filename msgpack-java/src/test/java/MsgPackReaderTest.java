import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class MsgPackReaderTest {

    @Test
    public void testReadWrite() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        MsgPackReader.Outer o = new MsgPackReader.Outer();
        MsgPackReader.Inner i1 = new MsgPackReader.Inner();
        i1.value = "val1";
        MsgPackReader.Inner i2 = new MsgPackReader.Inner();
        i2.value = "val2";
        o.inners = Arrays.asList(i1, i2);
        byte[] buf = mapper.writeValueAsBytes(o);
        File f = File.createTempFile("msgpackTest", "msgpack");
        f.deleteOnExit();
        Files.write(f.toPath(), buf, StandardOpenOption.CREATE);
        MsgPackReader.decodeMessagePackFile(f.toPath());
    }
}

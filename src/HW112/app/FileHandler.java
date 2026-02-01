package HW112.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileHandler {
    public String readFromFile(String path) {
        FileChannel channel;
        StringBuilder builder;
        try (FileInputStream fis = new FileInputStream(path)) {
            channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(128);
            builder = new StringBuilder();
            while (channel.read(buffer) != -1) {
                buffer.flip();
                builder.append(Charset.defaultCharset().decode(buffer));
                buffer.clear();
            }
            channel.close();
            return builder.toString();
        } catch (IOException e) {
            return "Something wrong " + e.getMessage();
        }
    }
}

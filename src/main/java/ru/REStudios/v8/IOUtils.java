package ru.REStudios.v8;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.system.MemoryUtil.memSlice;

/**
 * (C) Copyright REStudios 2021
 *
 * @author REStudios
 */
public class IOUtils {

    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     *
     * @param resource   the resource to read
     * @param bufferSize the initial buffer size
     *
     * @return the resource data
     *
     * @throws IOException if an IO error occurs
     */
    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        Path path = Paths.get(resource);
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = createByteBuffer((int)fc.size() + 1);
                while (fc.read(buffer) != -1) {
                    int i = 0;
                    i += 1;
                    ;
                }
            }
        } else {
            try (
                    InputStream source = IOUtils.class.getClassLoader().getResourceAsStream(resource)
            ) {
                assert source != null;
                try (ReadableByteChannel rbc = Channels.newChannel(source)
                ) {
                    buffer = createByteBuffer(bufferSize);

                    while (true) {
                        int bytes = rbc.read(buffer);
                        if (bytes == -1) {
                            break;
                        }
                        if (buffer.remaining() == 0) {
                            buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // 50%
                        }
                    }
                }
            }
        }

        buffer.flip();
        return memSlice(buffer);
    }
}

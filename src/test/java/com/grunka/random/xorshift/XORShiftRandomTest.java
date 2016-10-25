package com.grunka.random.xorshift;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

@Ignore
public class XORShiftRandomTest {

    @Test
    public void xorshift128() throws Exception {
        dumpData(new XORShift128(), Paths.get("random.128.out"));
    }

    @Test
    public void xorshift128plus() throws Exception {
        dumpData(new XORShift128Plus(), Paths.get("random.128plus.out"));
    }

    @Test
    public void xorshift64star() throws Exception {
        dumpData(new XORShift64Star(), Paths.get("random.64star.out"));
    }

    @Test
    public void xorshift1024star() throws Exception {
        dumpData(new XORShift1024Star(), Paths.get("random.1024star.out"));
    }

    private void dumpData(Random random, Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
        System.out.println("path.toRealPath() = " + path.toRealPath());
        byte[] fourK = new byte[4 * 1024];
        for (int i = 0; i < 1024 * 1024; i++) {
            random.nextBytes(fourK);
            Files.write(path, fourK, StandardOpenOption.APPEND);
        }
    }
}

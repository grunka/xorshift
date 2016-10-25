package com.grunka.random.xorshift;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

@Ignore
public class XORShiftRandomTest {

    @Test
    public void name() throws Exception {
        Random random = new XORShiftPlus128();
        Path path = Paths.get("random.out");
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

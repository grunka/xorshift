package com.grunka.random.xorshift;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XORShiftBaseTest {
    @Test
    public void testBitMasks() throws Exception {
        String expected = "1";
        for (int i = 1; i <= 32; i++) {
            assertEquals(expected, Integer.toBinaryString(XORShiftBase.getBitMask(i)));
            expected += "1";
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaskingTooFewBits() throws Exception {
        XORShiftBase.getBitMask(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaskingTooManyBits() throws Exception {
        XORShiftBase.getBitMask(33);
    }
}
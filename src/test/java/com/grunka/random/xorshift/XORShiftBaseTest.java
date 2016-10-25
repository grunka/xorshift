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

    @Test
    public void testBitShifting() throws Exception {
        XORShiftBase xorShift = new TextXORShift(0xdeadbeeffacefeedL);
        assertEquals("dead", Integer.toHexString(xorShift.next(16)));
        assertEquals("beef", Integer.toHexString(xorShift.next(16)));
        assertEquals("face", Integer.toHexString(xorShift.next(16)));
        assertEquals("feed", Integer.toHexString(xorShift.next(16)));
        assertEquals("de", Integer.toHexString(xorShift.next(8)));
        assertEquals("a", Integer.toHexString(xorShift.next(4)));
        assertEquals("d", Integer.toHexString(xorShift.next(4)));
    }

    private static class TextXORShift extends XORShiftBase {
        private long seed;

        public TextXORShift(long seed) {
            super(seed);
        }

        @Override
        void reset(long seed) {
            this.seed = seed;
        }

        @Override
        long next64Bits() {
            return seed;
        }
    }
}

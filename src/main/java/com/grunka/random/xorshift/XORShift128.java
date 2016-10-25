package com.grunka.random.xorshift;

public class XORShift128 extends XORShiftBase {
    private int x, y, z, w;

    public XORShift128() {
        super();
    }

    public XORShift128(long seed) {
        super(seed);
    }

    @Override
    void reset(long seed) {
        long[] expanded = expandSeed(seed, 2);
        x = (int) (expanded[0] >>> 32);
        y = (int) (expanded[0]);
        z = (int) (expanded[1] >>> 32);
        w = (int) (expanded[1]);
    }

    @Override
    long next64Bits() {
        long bits0 = (long) next32Bits();
        long bits1 = (long) next32Bits();
        return bits0 << 32 | (bits1 & 0xffffffffL);
    }

    private int next32Bits() {
        int t = x;
        t ^= t << 11;
        t ^= t >>> 8;
        x = y;
        y = z;
        z = w;
        w ^= w >>> 19;
        w ^= t;
        return w;
    }
}

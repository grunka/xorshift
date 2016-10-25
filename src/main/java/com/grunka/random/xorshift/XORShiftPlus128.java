package com.grunka.random.xorshift;

public class XORShiftPlus128 extends XORShiftBase {
    private long[] s;

    public XORShiftPlus128() {
        super();
    }

    public XORShiftPlus128(long seed) {
        super(seed);
    }

    @Override
    public void reset(long seed) {
        s = expandSeed(seed, 2);
    }

    @Override
    public long next64Bits() {
        long x = s[0];
        final long y = s[1];
        s[0] = y;
        x ^= x << 23; // a
        s[1] = x ^ y ^ (x >>> 17) ^ (y >>> 26); // b, c
        return s[1] + y;
    }
}

package com.grunka.random.xorshift;

public class XORShiftStar64 extends XORShiftBase {
    private long x;

    public XORShiftStar64() {
        super();
    }

    public XORShiftStar64(long seed) {
        super(seed);
    }

    @Override
    public void reset(long seed) {
        x = seed;
    }

    @Override
    public long next64Bits() {
        x ^= x >>> 12; // a
        x ^= x << 25;  // b
        x ^= x >>> 27; // c
        return x * 2685821657736338717L;
    }
}

package com.grunka.random.xorshift;

public class XORShift64Star extends XORShiftBase {
    private long x;

    public XORShift64Star() {
        super();
    }

    public XORShift64Star(long seed) {
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

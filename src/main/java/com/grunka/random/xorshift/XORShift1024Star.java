package com.grunka.random.xorshift;

public class XORShift1024Star extends XORShiftBase {
    private long[] s;
    private int p;

    public XORShift1024Star() {
        super();
    }

    public XORShift1024Star(long seed) {
        super(seed);
    }

    @Override
    void reset(long seed) {
        s = expandSeed(seed, 16);
    }

    @Override
    long next64Bits() {
        final long s0 = s[p];
        p = (p + 1) & 15;
        long s1 = s[p];
        s1 ^= s1 << 31; // a
        s[p] = s1 ^ s0 ^ (s1 >>> 11) ^ (s0 >>> 30); // b, c
        return s[p] * 1181783497276652981L;
    }
}

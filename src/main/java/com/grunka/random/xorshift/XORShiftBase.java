package com.grunka.random.xorshift;

import java.util.Random;

abstract class XORShiftBase extends Random {
    private long currentRandomBits = 0;
    private int currentRemainingRandomBits = 0;

    XORShiftBase() {
        super();
    }

    XORShiftBase(long seed) {
        super(seed);
    }

    @Override
    public synchronized void setSeed(long seed) {
        if (seed == 0) {
            throw new IllegalArgumentException("Seed cannot be zero");
        }
        reset(seed);
        currentRemainingRandomBits = 0;
    }

    @Override
    protected synchronized int next(int bits) {
        if (bits > 32) {
            throw new IllegalArgumentException("Requested bits greater than 32");
        }
        int result = 0;
        int bitsStillToTake = bits;
        while (bitsStillToTake > 0) {
            ensureBits();
            int bitsToTake;
            if (bitsStillToTake <= currentRemainingRandomBits) {
                bitsToTake = bitsStillToTake;
                bitsStillToTake = 0;
            } else {
                bitsToTake = currentRemainingRandomBits;
                bitsStillToTake = bitsStillToTake - currentRemainingRandomBits;
            }

            int mask = getBitMask(bitsToTake);
            result = result | ((int) (currentRandomBits >>> (currentRemainingRandomBits - bitsToTake))) & mask;
            result = result << bitsStillToTake;

            currentRemainingRandomBits -= bitsToTake;
        }
        return result;
    }

    static int getBitMask(int bitsToTake) {
        if (bitsToTake == 0) {
            return 0;
        }
        return ((1 << 31) >> (bitsToTake - 1)) >>> (32 - bitsToTake);
    }

    private void ensureBits() {
        if (currentRemainingRandomBits == 0) {
            currentRemainingRandomBits = 64;
            currentRandomBits = next64Bits();
        }
    }

    abstract void reset(long seed);

    abstract long next64Bits();

    static long[] expandSeed(long seed, int length) {
        if (seed == 0) {
            throw new IllegalArgumentException("Seed not allowed to be zero");
        }
        if (length < 1) {
            throw new IllegalArgumentException("Requested seed length too short");
        }
        long[] s = new long[length];
        s[0] = seed;
        final int w = Long.SIZE;
        final long f = 6364136223846793005L;
        for (int i = 1; i < s.length; i++) {
            s[i] = f * (s[i - 1] ^ (s[i - 1] >>> (w - 2))) + i;
        }
        return s;
    }
}

package pl.marek.sha3.transformation;

public class Iota {

    private static final long[] CALCULATED_ROUND_CONSTANT = new long[]{
            1L,
            32898L,
            -9223372036854742902L,
            -9223372034707259392L,
            32907L,
            2147483649L,
            -9223372034707259263L,
            -9223372036854743031L,
            138L,
            136L,
            2147516425L,
            2147483658L,
            2147516555L,
            -9223372036854775669L,
            -9223372036854742903L,
            -9223372036854743037L,
            -9223372036854743038L,
            -9223372036854775680L,
            32778L,
            -9223372034707292150L,
            -9223372034707259263L,
            -9223372036854742912L,
            2147483649L,
            -9223372034707259384L
    };

    public void transform(long[][] state, int round) {
        state[0][0] ^= CALCULATED_ROUND_CONSTANT[round];
    }
}

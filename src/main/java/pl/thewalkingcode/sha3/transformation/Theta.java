package pl.thewalkingcode.sha3.transformation;

public class Theta {

    private final long[] C;
    private final long[] D;

    public Theta() {
        C = new long[5];
        D = new long[5];
    }

    public void transform(long[][] state) {
        firstStep(state);
        secondStep();
        finalStep(state);
    }

    private void firstStep(long[][] state) {
        for (int x = 0; x < 5; x++) {
            C[x] = state[x][0] ^ state[x][1] ^ state[x][2] ^ state[x][3] ^ state[x][4];
        }
    }

    private void secondStep() {
        D[0] = C[4] ^ Long.rotateLeft(C[1], 1);
        D[1] = C[0] ^ Long.rotateLeft(C[2], 1);
        D[2] = C[1] ^ Long.rotateLeft(C[3], 1);
        D[3] = C[2] ^ Long.rotateLeft(C[4], 1);
        D[4] = C[3] ^ Long.rotateLeft(C[0], 1);
    }

    private void finalStep(long[][] state) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                state[x][y] ^= D[x];
            }
        }
    }
}

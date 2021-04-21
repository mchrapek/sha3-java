package pl.thewalkingcode.sha3.transformation;

public class Rho {

    private static final int[][] OFFSET_OF_RHO = new int[][]{
            {0, 36, 3, 41, 18},
            {1, 44, 10, 45, 2},
            {62, 6, 43, 15, 61},
            {28, 55, 25, 21, 56},
            {27, 20, 39, 8, 14}
    };

    public void transform(long[][] state) {
        state[0][0] = Long.rotateLeft(state[0][0], OFFSET_OF_RHO[0][0]);
        state[0][1] = Long.rotateLeft(state[0][1], OFFSET_OF_RHO[0][1]);
        state[0][2] = Long.rotateLeft(state[0][2], OFFSET_OF_RHO[0][2]);
        state[0][3] = Long.rotateLeft(state[0][3], OFFSET_OF_RHO[0][3]);
        state[0][4] = Long.rotateLeft(state[0][4], OFFSET_OF_RHO[0][4]);

        state[1][0] = Long.rotateLeft(state[1][0], OFFSET_OF_RHO[1][0]);
        state[1][1] = Long.rotateLeft(state[1][1], OFFSET_OF_RHO[1][1]);
        state[1][2] = Long.rotateLeft(state[1][2], OFFSET_OF_RHO[1][2]);
        state[1][3] = Long.rotateLeft(state[1][3], OFFSET_OF_RHO[1][3]);
        state[1][4] = Long.rotateLeft(state[1][4], OFFSET_OF_RHO[1][4]);

        state[2][0] = Long.rotateLeft(state[2][0], OFFSET_OF_RHO[2][0]);
        state[2][1] = Long.rotateLeft(state[2][1], OFFSET_OF_RHO[2][1]);
        state[2][2] = Long.rotateLeft(state[2][2], OFFSET_OF_RHO[2][2]);
        state[2][3] = Long.rotateLeft(state[2][3], OFFSET_OF_RHO[2][3]);
        state[2][4] = Long.rotateLeft(state[2][4], OFFSET_OF_RHO[2][4]);

        state[3][0] = Long.rotateLeft(state[3][0], OFFSET_OF_RHO[3][0]);
        state[3][1] = Long.rotateLeft(state[3][1], OFFSET_OF_RHO[3][1]);
        state[3][2] = Long.rotateLeft(state[3][2], OFFSET_OF_RHO[3][2]);
        state[3][3] = Long.rotateLeft(state[3][3], OFFSET_OF_RHO[3][3]);
        state[3][4] = Long.rotateLeft(state[3][4], OFFSET_OF_RHO[3][4]);

        state[4][0] = Long.rotateLeft(state[4][0], OFFSET_OF_RHO[4][0]);
        state[4][1] = Long.rotateLeft(state[4][1], OFFSET_OF_RHO[4][1]);
        state[4][2] = Long.rotateLeft(state[4][2], OFFSET_OF_RHO[4][2]);
        state[4][3] = Long.rotateLeft(state[4][3], OFFSET_OF_RHO[4][3]);
        state[4][4] = Long.rotateLeft(state[4][4], OFFSET_OF_RHO[4][4]);
    }
}

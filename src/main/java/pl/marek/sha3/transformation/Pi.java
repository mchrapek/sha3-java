package pl.marek.sha3.transformation;

public class Pi {

    public void transform(long[][] state) {
        long[][] temp = new long[5][5];
        for (int i = 0; i < state.length; i++) {
            System.arraycopy(state[i], 0, temp[i], 0, state[i].length);
        }

        state[0][0] = temp[0][0];
        state[1][3] = temp[0][1];
        state[2][1] = temp[0][2];
        state[3][4] = temp[0][3];
        state[4][2] = temp[0][4];

        state[0][2] = temp[1][0];
        state[1][0] = temp[1][1];
        state[2][3] = temp[1][2];
        state[3][1] = temp[1][3];
        state[4][4] = temp[1][4];

        state[0][4] = temp[2][0];
        state[1][2] = temp[2][1];
        state[2][0] = temp[2][2];
        state[3][3] = temp[2][3];
        state[4][1] = temp[2][4];

        state[0][1] = temp[3][0];
        state[1][4] = temp[3][1];
        state[2][2] = temp[3][2];
        state[3][0] = temp[3][3];
        state[4][3] = temp[3][4];

        state[0][3] = temp[4][0];
        state[1][1] = temp[4][1];
        state[2][4] = temp[4][2];
        state[3][2] = temp[4][3];
        state[4][0] = temp[4][4];
    }
}

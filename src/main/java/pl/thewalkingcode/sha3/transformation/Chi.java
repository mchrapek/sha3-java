package pl.thewalkingcode.sha3.transformation;

public class Chi {

    public void transform(long[][] state) {
        long[][] temp = new long[5][5];
        for (int i = 0; i < state.length; i++) {
            System.arraycopy(state[i], 0, temp[i], 0, state[i].length);
        }

        for (int y = 0; y < 5; ++y) {
            state[0][y] = temp[0][y] ^ (~temp[1][y] & temp[2][y]);
            state[1][y] = temp[1][y] ^ (~temp[2][y] & temp[3][y]);
            state[2][y] = temp[2][y] ^ (~temp[3][y] & temp[4][y]);
            state[3][y] = temp[3][y] ^ (~temp[4][y] & temp[0][y]);
            state[4][y] = temp[4][y] ^ (~temp[0][y] & temp[1][y]);
        }
    }
}

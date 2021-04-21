package pl.thewalkingcode.sha3.operations;

import pl.thewalkingcode.sha3.Config;
import pl.thewalkingcode.sha3.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Absorb {

    private final Type type;
    private final Permute permute;

    public Absorb(Type type) {
        this.type = type;
        this.permute = new Permute();
    }

    public void absorb(final byte[] input, final long[][] state) {
        int inputBitLength = input.length << 3;

        int indexBit = 0;
        do {
            int length = Math.min(Config.getRateBitsLength(type), inputBitLength - indexBit);
            absorbBits(state, input, indexBit, length);
            permute.permute(state);
            indexBit += Config.getRateBitsLength(type);
        } while (indexBit < inputBitLength);
    }

    public int absorb(final InputStream stream, final long[][] state, final byte[] input) throws IOException {
        int chunkBitsLength = readBytesFromStream(stream, input);

        while (chunkBitsLength == Config.getRateBitsLength(type)) {
            absorbBits(state, input, 0, chunkBitsLength);
            permute.permute(state);
            chunkBitsLength = readBytesFromStream(stream, input);
        }

        return chunkBitsLength;
    }

    private int readBytesFromStream(final InputStream stream, final byte[] input) throws IOException {
        int indexBytes = 0;
        int readBytes = stream.read(input);

        while (readBytes > 0) {
            indexBytes += readBytes;
            readBytes = stream.read(input, indexBytes, input.length - indexBytes);
        }

        if (indexBytes < input.length) {
            Arrays.fill(input, indexBytes, input.length, (byte) 0);
        }

        return indexBytes * Byte.SIZE;
    }

    private void absorbBits(final long[][] state, final byte[] input, final int indexBit, final int lengthToRead) {
        int index = indexBit;
        int toRead = lengthToRead;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {

                if (index % Byte.SIZE == 0 && toRead >= Config.LANE_BITS_LENGTH) {
                    absorbLane(state, input, index, x, y);
                    index += Config.LANE_BITS_LENGTH;
                    toRead -= Config.LANE_BITS_LENGTH;
                } else {
                    absorbBitByBit(state, input, index, toRead, x, y);
                    return;
                }

            }
        }
    }

    private void absorbLane(final long[][] state, final byte[] input, final int indexBit, final int x, final int y) {
        int laneByteLength = Config.LANE_BITS_LENGTH / Byte.SIZE;
        int indexByte = indexBit / Byte.SIZE;

        long laneValue = 0L;
        for (int i = laneByteLength - 1; i >= 0; i--) {
            laneValue <<= Byte.SIZE;
            laneValue += Byte.toUnsignedInt(input[indexByte + i]);
        }
        state[x][y] ^= laneValue;
    }

    private void absorbBitByBit(final long[][] state, final byte[] input, final int indexBit, final int length, final int x, final int y) {
        int lastBit = indexBit + length;
        int z = 0;
        int tempX = x;
        int tempY = y;

        for (int i = indexBit; i < lastBit; i++) {
            if (isInputBitHigh(input, i)) {
                state[tempX][tempY] ^= (1L << z);
            }

            if (++z == Config.LANE_BITS_LENGTH) {
                ++tempX;
                z = 0;
            }

            if (x == 5) {
                ++tempY;
                tempX = 0;
            }
        }
    }

    private boolean isInputBitHigh(final byte[] input, final int indexBit) {
        int indexByte = indexBit / Byte.SIZE;
        int indexBitByte = indexBit % Byte.SIZE;
        return 0 != (input[indexByte] & (1 << indexBitByte));
    }
}

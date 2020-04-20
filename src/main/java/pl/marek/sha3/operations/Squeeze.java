package pl.marek.sha3.operations;

import pl.marek.sha3.Config;
import pl.marek.sha3.Type;

public class Squeeze {

    private final Type type;

    public Squeeze(Type type) {
        this.type = type;
    }

    public byte[] squeeze(final long[][] state, final byte[] output) {
        int outputBitLength = type.getDigestBitsLength();

        int outputBit = 0;
        do {
            int length = Math.min(Config.getRateBitsLength(type), outputBitLength - outputBit);
            squeezeBitsFromState(state, output, outputBit, length);
            outputBit += Config.getRateBitsLength(type);
        } while (outputBit < outputBitLength);

        return output;
    }

    private void squeezeBitsFromState(final long[][] state, final byte[] output, final int outputBit, final int lengthToWrite) {
        int index = outputBit;
        int stopIndex = outputBit + lengthToWrite;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {

                if (index == stopIndex) {
                    return;
                }

                if (index % Byte.SIZE == 0 && lengthToWrite - index >= Config.LANE_BITS_LENGTH) {
                    squeezeLane(state, output, index, x, y);
                    index += Config.LANE_BITS_LENGTH;
                } else {
                    index = squeezeBitByBit(state, output, index, stopIndex, x, y);
                }

            }
        }
    }

    void squeezeLane(final long[][] state, final byte[] output, final int indexBit, final int x, final int y) {
        int laneByteLength = Config.LANE_BITS_LENGTH / Byte.SIZE;
        int indexByte = indexBit / Byte.SIZE;

        long laneValue = state[x][y];
        for (int i = laneByteLength - 1; i >= 0; i--) {
            byte value = (byte) (laneValue & 0xff);
            output[indexByte + (laneByteLength - 1 - i)] = value;
            laneValue >>= Byte.SIZE;
        }
    }

    int squeezeBitByBit(final long[][] state, final byte[] output, final int indexBit, final int stopIndex, final int x, final int y) {
        int index = indexBit;

        for (int z = 0; z < Config.LANE_BITS_LENGTH; z++) {
            if (index == stopIndex) {
                break;
            }

            if (isBitHigh(state, x, y, z)) {
                setBitHigh(output, index);
            }

            index++;
        }

        return index;
    }

    private boolean isBitHigh(final long[][] state, final int x, final int y, final int z) {
        return (state[x][y] & (1L << z)) != 0;
    }

    private void setBitHigh(final byte[] output, final int indexBit) {
        int targetByte = indexBit / Byte.SIZE;
        byte targetBitByteValue = (byte) (indexBit % Byte.SIZE);
        output[targetByte] += (byte) (1 << targetBitByteValue);
    }
}

package pl.mchrapek.sha3;

import pl.mchrapek.sha3.exceptions.AlgorithmInvalidState;
import pl.mchrapek.sha3.operations.Absorb;
import pl.mchrapek.sha3.operations.Padding;
import pl.mchrapek.sha3.operations.Squeeze;

import java.io.IOException;
import java.io.InputStream;

public class Sha3 {

    private final Type type;

    private final long[][] state = new long[5][5];

    private final int rateBitsLength;
    private final int capacityBitsLength;

    private final Padding padding;
    private final Squeeze squeeze;
    private final Absorb absorb;

    public Sha3(Type type) {
        this.type = type;
        this.rateBitsLength = Config.getRateBitsLength(type);
        this.capacityBitsLength = Config.getCapacityBitsLength(type);

        this.padding = new Padding(type);
        this.absorb = new Absorb(type);
        this.squeeze = new Squeeze(type);
    }

    public byte[] encode(final byte[] data) {
        if (data == null) {
            throw new AlgorithmInvalidState("Data cannot be null");
        }

        initLane();

        int dataBitsLength = data.length << 3;
        int inputLength = getInputLength(dataBitsLength);
        byte[] input = createArray(inputLength);
        copyDataToInput(data, input);

        addSuffixBits(input, dataBitsLength);

        padding.padding101(input, dataBitsLength);

        absorb.absorb(input, state);

        byte[] output = createArray(type.getDigestBitsLength());
        return this.squeeze.squeeze(state, output);
    }

    public byte[] encode(final InputStream stream) throws IOException {
        if (stream == null) {
            throw new AlgorithmInvalidState("File cannot be null");
        }

        initLane();

        byte[] input = createArray(Config.getRateBitsLength(type));
        int finalChunkBitsLength = this.absorb.absorb(stream, state, input);
        byte[] finalChunk = createAndCopyFinalChunk(input, finalChunkBitsLength);

        addSuffixBits(finalChunk, finalChunkBitsLength);

        padding.padding101(finalChunk, finalChunkBitsLength);

        absorb.absorb(finalChunk, state);

        byte[] output = createArray(type.getDigestBitsLength());
        return squeeze.squeeze(state, output);
    }

    private byte[] createAndCopyFinalChunk(final byte[] input, final int finalChunkBitsLength) {
        int lengthWithPadding = getInputLength(finalChunkBitsLength);

        return lengthWithPadding > Config.getRateBitsLength(type) ? resizedFinalChunk(input, finalChunkBitsLength, lengthWithPadding) : input;
    }

    private byte[] resizedFinalChunk(final byte[] input, final int finalChunkBitsLength, final int lengthWithPadding) {
        int bytesRequired = getNeededBytes(lengthWithPadding, Config.getRateBitsLength(type));
        byte[] finalChunk = new byte[bytesRequired * Config.getRateBitsLength(type) / Byte.SIZE];

        int bytesToCopy = getNeededBytes(finalChunkBitsLength, Byte.SIZE);
        System.arraycopy(input, 0, finalChunk, 0, bytesToCopy);

        return finalChunk;
    }

    private int getNeededBytes(final int dividendBits, final int divisorBits) {
        if (dividendBits == 0) return 0;

        return dividendBits % divisorBits == 0 ? dividendBits / divisorBits : 1 + dividendBits / divisorBits;
    }

    private void initLane() {
        for (int x = 0; x < 5; ++x) {
            for (int y = 0; y < 5; ++y) {
                state[x][y] = 0L;
            }
        }
    }

    public int getInputLength(final int dataBitsLength) {
        int inputLength = dataBitsLength + 1 + Config.SUFFIX_BITS.length() + 1;

        return inputLength % rateBitsLength == 0 ? inputLength : inputLength + rateBitsLength - inputLength % rateBitsLength;
    }

    private byte[] createArray(final int lengthInBits) {
        int arrayLength = lengthInBits / Byte.SIZE;
        if (lengthInBits % Byte.SIZE != 0) {
            arrayLength += 1;
        }
        return new byte[arrayLength];
    }

    private void copyDataToInput(final byte[] data, final byte[] input) {
        System.arraycopy(data, 0, input, 0, data.length);
    }

    private void addSuffixBits(final byte[] input, final int dataBitsLength) {
        for (int i = 0; i < Config.SUFFIX_BITS.length(); i++) {
            if (Config.SUFFIX_BITS.charAt(i) == '1') {
                int targetBit = dataBitsLength + i;
                int targetByte = targetBit / Byte.SIZE;
                int targetBitByte = targetBit % Byte.SIZE;
                input[targetByte] += 1 << targetBitByte;
            }
        }
    }
}

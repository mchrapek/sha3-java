package pl.marek.sha3.operations;

import pl.marek.sha3.Config;
import pl.marek.sha3.Type;

public class Padding {

    private final Type type;

    public Padding(Type type) {
        this.type = type;
    }

    public void padding101(final byte[] input, final int dataBitsLength) {
        int dataWithSuffixBitsLength = dataWithSuffixBitsLength(dataBitsLength);
        int positionPaddingBits = positionPaddingBits(dataBitsLength);

        setPaddingBit(input, dataWithSuffixBitsLength);
        setPaddingBit(input, dataWithSuffixBitsLength + 1 + positionPaddingBits);
    }

    private void setPaddingBit(final byte[] input, final int indexBit) {
        int targetByte = indexBit / Byte.SIZE;
        byte targetBitByteValue = (byte) (indexBit % Byte.SIZE);
        input[targetByte] += (byte) (1 << targetBitByteValue);
    }

    private int positionPaddingBits(final int dataBitsLength) {
        int dataWithSuffixAndPaddingBitsLength = dataWithSuffixAndPaddingBitsLength(dataBitsLength);
        if (dataWithSuffixAndPaddingBitsLength % Config.getRateBitsLength(type) != 0) {
            return Config.getRateBitsLength(type) - dataWithSuffixAndPaddingBitsLength % Config.getRateBitsLength(type);
        } else {
            return 0;
        }
    }

    private int dataWithSuffixBitsLength(final int dataBitsLength) {
        return dataBitsLength + Config.SUFFIX_BITS.length();
    }

    private int dataWithSuffixAndPaddingBitsLength(final int dataBitsLength) {
        return dataWithSuffixBitsLength(dataBitsLength) + 2;
    }
}

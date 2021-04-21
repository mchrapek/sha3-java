package pl.mchrapek.sha3;

public class Config {

    public static final int ROUNDS = 24;
    public static final int LANE_BITS_LENGTH = 64;
    public static final String SUFFIX_BITS = "01";

    public static int getRateBitsLength(final Type type) {
        switch (type) {
            case SHA3_224:
                return 1152;
            case SHA3_256:
                return 1088;
            case SHA3_384:
                return 832;
            case SHA3_512:
                return 576;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int getCapacityBitsLength(final Type type) {
        switch (type) {
            case SHA3_224:
                return 448;
            case SHA3_256:
                return 512;
            case SHA3_384:
                return 768;
            case SHA3_512:
                return 1024;
            default:
                throw new IllegalArgumentException();
        }
    }
}

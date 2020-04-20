package pl.marek.sha3;

public enum Type {
    SHA3_224(224),
    SHA3_256(256),
    SHA3_384(384),
    SHA3_512(512);

    private final int digestBitsLength;

    Type(int digestBitsLength) {
        this.digestBitsLength = digestBitsLength;
    }

    public int getDigestBitsLength() {
        return digestBitsLength;
    }
}

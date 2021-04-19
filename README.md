# SHA3 Java Implementation

[![.github/workflows/actions.yml](https://github.com/mchrapek/sha3-java/actions/workflows/actions.yml/badge.svg?branch=master)](https://github.com/mchrapek/sha3-java/actions/workflows/actions.yml)

This is a java implementation of SHA3 (Keccak), based on the:
[PDF](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.202.pdf).

---

## Example of usage:

### For the text:
```
String input = "...";

Type type = Type.SHA3_256;
Sha3 sha3 = new Sha3(type);

byte[] encode = sha3.encode(inputStream);
```

### For the file:
```
File file = new File("...");
InputStream inputStream = new FileInputStream(file);

Type type = Type.SHA3_256;
Sha3 sha3 = new Sha3(type);

byte[] encode = sha3.encode(inputStream);
```

### Supported types:
```
Type type = Type.SHA3_224 / Type.SHA3_256 / Type.SHA3_384 / Type.SHA3_512; 
```

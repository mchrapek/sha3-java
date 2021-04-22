# SHA3 Java Implementation

[![build](https://github.com/mchrapek/sha3-java/actions/workflows/actions.yml/badge.svg)](https://github.com/mchrapek/sha3-java/actions/workflows/actions.yml)
[![codecov](https://codecov.io/gh/mchrapek/sha3-java/branch/master/graph/badge.svg)](https://codecov.io/gh/mchrapek/sha3-java)


This is a java implementation of SHA3 (Keccak), based on the:
[PDF](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.202.pdf).

---

## Dependency

### Maven

```
<dependency>
  <groupId>pl.thewalkingcode.sha3</groupId>
  <artifactId>sha3-java</artifactId>
  <version>1.1.1</version>
</dependency>
```

### Gradle
```
implementation 'pl.thewalkingcode.sha3:sha3-java:1.1.1'
```

---

## Usage

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

### Repositories with an example of usage:

* [Data Consistency Verifier](https://github.com/mchrapek/data-consistency-verifier-sha3)

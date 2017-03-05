# msgpacktest
Reproduces inompatibility between Rust serde and Java Jackson message pack serialization.

This repository consists of two projects, **msgpack-java** and **msgpack-rust**, that serialize a data structure in rust and tries to read the same data structure in Java.

Requires that Java 1.8 and Rust are installed

# In msgpack-rust
Build project
```cargo build --release
```
Generate msgpack file
```./target/release/msgpack-rust
```
# In msgpack-java
Build project
```./gradlew jar
```

Read Rust-generated msgpack file
```java -jar ./build/libs/msgpack-reader-1.0-SNAPSHOT.jar ../msgpack-rust/rustoutput.msgpack
```

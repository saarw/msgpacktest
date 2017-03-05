# msgpacktest
Reproduces inompatibility between Rust serde and Java Jackson message pack serialization.

This repository consists of two projects, msgpack-java and msgpack-rust, that serialize a data structure in rust and tries to read the same data structure in Java.

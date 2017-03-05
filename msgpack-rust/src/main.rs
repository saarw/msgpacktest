extern crate serde;
#[macro_use] extern crate serde_derive;
extern crate rmp_serde;

use std::fs::File;
use std::io::Write;


#[derive(Debug, PartialEq, Serialize, Deserialize)]
struct Outer {
    inners: Vec<Inner>
}

#[derive(Debug, PartialEq, Serialize, Deserialize)]
struct Inner {
    value: String
}

#[test]
fn test_encode_decode() {
    let val = Outer{inners: vec!(Inner { value: "val1".to_string() }, Inner { value: "val2".to_string() })};
    let result = rmp_serde::to_vec(&val);
    let decoded = rmp_serde::from_slice::<Outer>(&result.unwrap());
    assert_eq!(decoded.unwrap(), val);
}

// Writes a msgpack serialized Outer to a file
fn main() { ;
    let val = Outer{inners: vec!(Inner { value: "val1".to_string() }, Inner { value: "val2".to_string() })};
    println!("Write {:?}", File::create("rustoutput.msgpack").unwrap().write_all(&rmp_serde::to_vec(&val).unwrap()));
}

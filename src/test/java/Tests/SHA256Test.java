package Tests;

import Encryption.SHA256;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SHA256Test {

    @Test
    void test1() {
        Assertions.assertEquals("f0e4c2f76c58916ec258f246851bea091d14d4247a2fc3e18694461b1816e13b",SHA256.getHashedString("asdf"));
    }

    @Test
    void test2() {
        Assertions.assertEquals("f2ce1e9f76f0c986536a2f77edac98cc7bd4c6becb9361bc5fd2ebfa5a67855c",SHA256.getHashedString("T€5t"));
    }

    @Test
    void test3() {
        Assertions.assertEquals("4589d8594650f1f67a07ca11c9e8eb6c137685ff566d09ff64adfab8a6d085be",SHA256.getHashedString("Giggity"));
    }

}
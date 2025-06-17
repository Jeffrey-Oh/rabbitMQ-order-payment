package com.jeffrey.orderapi;


import com.jeffrey.orderapi.utils.AES256Util;
import org.junit.jupiter.api.Test;

class GenerateAes256Key {

    @Test
    void generateFixedIv() {
        var generateFixedIv = AES256Util.generateFixedIv();
        System.out.println("AES256 IV: " + generateFixedIv);
    }

    @Test
    void generateAes256Key() {
        var generateAes256Key = AES256Util.generateAes256Key();
        System.out.println("AES256 Key: " + generateAes256Key);
    }

}
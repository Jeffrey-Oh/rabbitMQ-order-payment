package com.jeffrey.orderapi.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AES256Util {

    private static final int AES_KEY_SIZE = 32; // 256-bit
    private static final int AES_IV_SIZE = 16;  // 128-bit

    private static SecretKeySpec keySpec;
    private static IvParameterSpec ivSpec;

    /**
     * 1회 고정 IV 생성용 유틸
     */
    public static String generateFixedIv() {
        byte[] ivBytes = new byte[AES_IV_SIZE];
        new SecureRandom().nextBytes(ivBytes);

        StringBuilder hexIv = new StringBuilder();
        for (byte b : ivBytes) {
            hexIv.append(String.format("%02x", b));
        }
        return hexIv.toString().substring(0, AES_IV_SIZE); // 16자리
    }

    /**
     * AES 256 비밀 키 생성용 (Base64 인코딩된 32바이트 키)
     */
    public static String generateAes256Key() {
        byte[] keyBytes = new byte[AES_KEY_SIZE];
        new SecureRandom().nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    /**
     * AES256 암호화 유틸 초기화
     */
    public static void init(String base64Key, String fixedIv) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        if (decodedKey.length != AES_KEY_SIZE) {
            throw new IllegalArgumentException("Key must be 32 bytes (256 bits) for AES-256");
        }
        keySpec = new SecretKeySpec(decodedKey, "AES");

        if (fixedIv.length() != AES_IV_SIZE) {
            throw new IllegalArgumentException("IV must be exactly 16 characters (128 bits)");
        }
        ivSpec = new IvParameterSpec(fixedIv.getBytes());
    }

    public static String encrypt(String value) {
        if (value == null || value.isBlank() || keySpec == null || ivSpec == null) {
            return value;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("AES encryption failed", e);
        }
    }

    public static String decrypt(String value) {
        if (value == null || value.isBlank() || keySpec == null || ivSpec == null) {
            return value;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decoded = Base64.getDecoder().decode(value);
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            throw new RuntimeException("AES decryption failed", e);
        }
    }
}
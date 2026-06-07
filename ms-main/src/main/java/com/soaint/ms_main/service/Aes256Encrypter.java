package com.soaint.ms_main.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Slf4j
public class Aes256Encrypter {

    private final String ALGORITHM = "AES/CBC/PKCS5Padding";

    @Value("${com.soaint.aes.key}")
    private String secretKey;
    @Value("${com.soaint.aes.iv}")
    private String ivString;

    private byte[] key;
    private byte[] iv;

    public String decrypt(String cipherText) throws Exception {
        log.debug("Decrypting cipher text: {}", cipherText);
        validateInputs();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(this.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
        log.debug("Decrypted text: {}", decryptedText);
        return decryptedText;
    }

    private void validateInputs() {
        log.debug("Validing inputs");
        this.key = secretKey.getBytes(StandardCharsets.UTF_8);
        this.iv = ivString.getBytes(StandardCharsets.UTF_8);
        if (key == null || key.length != 32) {
            throw new IllegalArgumentException("Invalid key length");
        }
        if (iv == null || iv.length != 16) {
            throw new IllegalArgumentException("Invalid IV length");
        }
    }
}

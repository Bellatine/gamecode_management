package com.namng7.gamecodemanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class AesEncryptionServiceImpl implements AesEncryptionService {

    @Value("${encryption.secretKey}")
    private String secretKey;  // Secret key của bạn

    @Override
    public String encrypt(String input) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);  // Mã hóa và chuyển sang Base64 để dễ dàng lưu trữ
    }

    @Override
    public String decrypt(String input) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedInput = Base64.getDecoder().decode(input);
        byte[] decrypted = cipher.doFinal(decodedInput);
        return new String(decrypted);
    }
}

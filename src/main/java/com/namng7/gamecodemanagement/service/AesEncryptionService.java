package com.namng7.gamecodemanagement.service;

public interface AesEncryptionService {
    String encrypt(String input) throws Exception;
    String decrypt(String input) throws Exception;
}

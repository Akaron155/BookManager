package com.lcd.service;

import com.lcd.pojo.Key;

import javax.crypto.SecretKey;

public interface KeyService {
    /**
     * 存储密钥
     * @param userId 用户ID
     * @param userType 用户类型（例如 "user" 或 "admin"）
     * @param secretKey 密钥字符串
     */
    void storeKey(Integer userId, String userType, SecretKey secretKey);

    /**
     * 根据用户ID和用户类型检索密钥
     * @param userId 用户ID
     * @param userType 用户类型
     * @return Key 对象，包含密钥信息
     */
    SecretKey getKeyByUserIdAndType(Integer userId, String userType);
}
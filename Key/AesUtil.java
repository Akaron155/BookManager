package com.lcd.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesUtil {

    // 定义AES加密算法的名称
    private static final String ALGORITHM = "AES";
    private static final String AES_KEY = "XALlBYV28p2J0dQC0VnYCyJbqVgC+3+GSzmFcvqCVQY=";

    /**
     * 生成AES密钥
     *
     * @return SecretKey对象，表示生成的AES密钥
     */
    public static SecretKey generateKey() {
        try {
            // 获取AES密钥生成器实例
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            // 设置密钥的长度，这里选择使用256位密钥（可以根据需要选择128位、192位或256位）
            keyGen.init(256);
            // 生成并返回密钥
            return keyGen.generateKey(); // 生成随机密钥
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 返回null表示密钥生成失败
            return null;
        }
    }

    /**
     * 将SecretKey转换为Base64编码的字符串
     * 这样做可以方便地存储密钥，例如将密钥存储在数据库中或通过网络传输
     *
     * @param secretKey AES密钥
     * @return Base64编码的密钥字符串
     */
    public static String keyToString(SecretKey secretKey) {
        // 将密钥的字节数组转换为Base64字符串
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 从Base64编码的字符串恢复SecretKey
     * 这可以用来从存储的密钥字符串恢复密钥
     *
     * @param keyStr Base64编码的密钥字符串
     * @return 恢复后的SecretKey对象
     */
    public static SecretKey stringToKey(String keyStr) {
        // 将Base64编码的字符串解码为字节数组
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        // 使用字节数组恢复为SecretKey对象
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    /**
     * 使用AES算法加密字符串
     * 该方法将输入的数据（字符串）加密为Base64编码的字符串
     *
     * @param data 要加密的字符串
     * @param secretKey 用于加密的AES密钥
     * @return 加密后的字符串，Base64编码
     */
    public static String encrypt(String data, SecretKey secretKey) {
        try {
            // 创建Cipher对象，指定加密算法（AES）
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化Cipher为加密模式，并传入密钥
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // 执行加密操作，将字符串转换为字节数组后加密
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            // 将加密后的字节数组转换为Base64编码的字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 返回null表示加密失败
            return null;
        }
    }

    /**
     * 使用AES算法解密字符串
     * 该方法将Base64编码的加密字符串解密为原始的明文字符串
     *
     * @param encryptedData Base64编码的加密字符串
     * @param secretKey 用于解密的AES密钥
     * @return 解密后的字符串（明文）
     */
    public static String decrypt(String encryptedData, SecretKey secretKey) {
        try {
            // 创建Cipher对象，指定解密算法（AES）
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化Cipher为解密模式，并传入密钥
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // 将Base64编码的加密字符串解码为字节数组
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            // 执行解密操作，得到解密后的字节数组
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            // 将解密后的字节数组转换为字符串并返回
            return new String(decryptedBytes);
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 返回null表示解密失败
            return null;
        }
    }

    /**
     * 将字节数组转换为SecretKey对象
     * 该方法通常用于将密钥的字节数组恢复为SecretKey对象
     *
     * @param keyBytes 字节数组表示的密钥
     * @return 恢复后的SecretKey对象
     */
    public static SecretKey bytesToKey(byte[] keyBytes) {
        // 使用字节数组创建一个SecretKeySpec对象，表示AES密钥
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    /**
     * 检查给定的密码在加密后是否与提供的加密字符串相等
     *
     * @param password 明文密码
     * @param encryptedPwdStr Base64编码的加密字符串
     * @param secretKey 用于加密的AES密钥
     * @return 如果加密后的密码与提供的加密字符串相等，返回true；否则返回false
     */
    public static boolean checkPassword(String password, String encryptedPwdStr, SecretKey secretKey) {
        // 使用AES加密给定的密码
        String encryptedPassword = encrypt(password, secretKey);
        // 比较加密后的密码与提供的加密字符串
        return encryptedPassword != null && encryptedPassword.equals(encryptedPwdStr);
    }
}
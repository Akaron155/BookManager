package com.lcd.service.impl;

import javax.crypto.SecretKey;
import javax.swing.text.Keymap;

import com.lcd.mapper.KeyMapper;
import com.lcd.pojo.Key;
import com.lcd.service.KeyService;
import com.lcd.utils.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KeyServicelmpl implements KeyService {

    @Autowired
    private KeyMapper keyMapper;

    // 生成新的AES密钥
    public static SecretKey generateKey() {
        return AesUtil.generateKey();
    }

    // 存储密钥到数据库
    @Override
    public void storeKey(Integer userId, String userType, SecretKey secretKey) {
//        将secrkey转换为Base64编码的字符串
        String secretKeyStr = AesUtil.keyToString(secretKey);

        //        创建一个Key对象
        Key key = new Key(userId,userType,secretKeyStr);
        keyMapper.insert(key);

    }
    // 从数据库检索密钥
    @Override
    public SecretKey getKeyByUserIdAndType(Integer userId, String userType) {
//        使用自定义的查询方法
        Key key = keyMapper.getKeyByUserIdAndType(userId,userType);
        if (key != null){
//            将Base64编码的字符串转换回Secretkey
            return AesUtil.stringToKey(key.getSecretKey());
        }
       return null;
    }
}
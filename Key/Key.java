package com.lcd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@TableName("secretkey")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Key {
    private Integer userId; // 用户ID
    private String userType; // 用户类型（例如 "user" 或 "admin"）
    private String secretKey; // 密钥（以字符串形式存储）
}
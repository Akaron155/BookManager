package com.lcd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcd.pojo.Key;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.crypto.SecretKey;

@Mapper
public interface KeyMapper extends BaseMapper<Key> {
    Key getKeyByUserIdAndType(Integer id, String userType);

//    @Select("SELECT * FROM keys WHERE user_id = #{userId} AND user_type = #{userType}")
//    Key getKeyByUserIdAndType(int userId, String userType);
}
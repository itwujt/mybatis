package com.bestwu.mybatis.mapper;

import com.bestwu.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Best Wu
 * @date 2021/3/24 16:58 <br>
 */
@Mapper
public interface UserMapper {


    List<UserEntity> queryList();

    UserEntity queryById(Long userId);

    UserEntity queryByNameAndPassword(UserEntity userEntity);
}

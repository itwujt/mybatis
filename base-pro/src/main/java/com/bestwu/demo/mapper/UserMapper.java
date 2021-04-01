package com.bestwu.demo.mapper;

import com.bestwu.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 *  demo 的 用户 mapper
 * @author Best Wu
 * @date 2020/6/26 13:20 <br>
 */
@Mapper
public interface UserMapper {
    /**
     * 无条件查询所有用户
     * @return java.util.List&lt;com.bestwu.demo.entity.UserEntity&gt;
     */
    List<UserEntity> queryAll();
}

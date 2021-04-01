package com.bestwu.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Best Wu
 * @date 2021/3/24 8:43 <br>
 */
@Data
@Accessors(chain = true)
@Alias("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5391208635510658703L;

    private Long userId;

    private String username;

    private String password;

    private Long creatorId;

    private LocalDateTime createTime;

    private Long modifierId;

    private LocalDateTime modifyTime;

    private Boolean enableFlag;
}

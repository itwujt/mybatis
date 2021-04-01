package com.bestwu.jdbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Best Wu
 * @date 2021/3/24 8:43 <br>
 */
@Data
@Accessors(chain = true)
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -1286627159848604476L;

    private Long userId;

    private String username;

    private String password;

    private Long creatorId;

    private LocalDateTime createTime;

    private Long modifierId;

    private LocalDateTime modifyTime;

    private Boolean enableFlag;
}

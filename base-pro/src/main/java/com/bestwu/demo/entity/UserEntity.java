package com.bestwu.demo.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <br>
 * demo 的用户实体类
 * @author Best Wu
 * @date 2020/6/26 13:09 <br>
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 5561886495073627905L;

    private Integer userId;

    private String userCode;

    private String username;

    private String telephone;

    private String password;

    private String userSex;

    private String userAddress;

    private String note;

    private String creatorCode;

    private LocalDateTime createTime;

    private String modifierCode;

    private LocalDateTime modifyTime;

    private Boolean enableFlag;

}

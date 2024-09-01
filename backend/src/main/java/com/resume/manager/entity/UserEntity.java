package com.resume.manager.entity;

import com.resume.manager.constant.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
@Table(indexes = {
        @Index(name = "usernameIndex", columnList = "username", unique = true)
})
public class UserEntity extends BaseEntity {

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private Long defaultResumeId;


}

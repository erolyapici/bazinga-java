package com.bazinga.user.model.entity;

import com.bazinga.base.model.entirty.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bazinga_users_roles", catalog = "bazinga")
public class UserRoleEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer userRoleId;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity user;

}

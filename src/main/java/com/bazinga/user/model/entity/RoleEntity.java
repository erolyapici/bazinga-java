package com.bazinga.user.model.entity;

import com.bazinga.base.model.entirty.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bazinga_roles", catalog = "bazinga")
public class RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer roleId;
    @Column(name = "ROLE")
    private String role;

}

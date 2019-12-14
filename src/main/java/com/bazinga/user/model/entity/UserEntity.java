package com.bazinga.user.model.entity;

import com.bazinga.base.model.entirty.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "bazinga_users", catalog = "bazinga")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "IDATE")
    private Date insertDate;
    @Column(name = "UDATE",nullable = true)
    private Date updateDate;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRoleEntity> roles;

}

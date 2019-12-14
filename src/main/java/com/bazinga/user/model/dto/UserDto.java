package com.bazinga.user.model.dto;

import com.bazinga.base.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto extends BaseDto {
    private Integer id;
    private String username;
    private String password;
    private String surname;
    private String name;
    private Integer state;
    private Date insertDate;
    private Date updateDate;
    private List<String> roles;

}

package com.future.sm.xt.pojo;

import lombok.Data;


@Data
public class User extends BasePojo{
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;

}

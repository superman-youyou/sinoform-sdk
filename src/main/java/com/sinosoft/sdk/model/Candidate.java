package com.sinosoft.sdk.model;

import lombok.Data;

import java.util.List;

@Data
public class Candidate {

    private List<User> users;
    private List<Dept> depts;
    private List<Role> roles;
}

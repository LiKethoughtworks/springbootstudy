package com.entity.hello.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("role_code")
    private String roleCode;

    @JsonProperty("privilege_codes")
    private List<String> privilegeCodes;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<String> getPrivilegeCodes() {
        return privilegeCodes;
    }

    public void setPrivilegeCodes(List<String> privilegeCodes) {
        this.privilegeCodes = privilegeCodes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

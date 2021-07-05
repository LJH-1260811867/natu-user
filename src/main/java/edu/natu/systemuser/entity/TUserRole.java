package edu.natu.systemuser.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljh
 * @since 2021-07-04
 */
public class TUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userAccount;

    private String roleId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "TUserRole{" +
        "id=" + id +
        ", userAccount=" + userAccount +
        ", roleId=" + roleId +
        "}";
    }
}

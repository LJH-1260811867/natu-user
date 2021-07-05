package edu.natu.systemuser.entity;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljh
 * @since 2021-07-04
 */
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String account;

    private String passwd;

    private String sex;

    private String name;

    private LocalDate birthday;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "TUser{" +
        "id=" + id +
        ", account=" + account +
        ", passwd=" + passwd +
        ", sex=" + sex +
        ", name=" + name +
        ", birthday=" + birthday +
        "}";
    }
}

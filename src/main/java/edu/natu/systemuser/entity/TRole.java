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
public class TRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String code;

    private String name;

    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TRole{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", description=" + description +
        "}";
    }
}

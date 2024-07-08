package com.neusoft.ysu.nepmserve.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-30
 */
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + id +
            ", state=" + state +
        "}";
    }
}

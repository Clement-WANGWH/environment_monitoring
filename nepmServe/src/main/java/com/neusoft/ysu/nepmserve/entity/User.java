package com.neusoft.ysu.nepmserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 性别	0：男	1：女	
     */
    private Integer sex;

    private String birthday;

    private Integer pId;

    private Integer cId;

    private Integer tId;

    /**
     * 网格员状态：	0：可工作，	1：临时抽调，	2：休假，	3：其他
     */
    private Integer state;

    private String avatar;

    /**
     * 角色：	1：系统管理员	2：网格员	3：公众监督员
     */
    private Integer roleId;

    @TableField(exist = false)
    private Grid province;

    @TableField(exist = false)
    private Grid city;

    @TableField(exist = false)
    private Grid town;

    @TableField(exist = false)
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Grid getProvince() {
        return province;
    }

    public void setProvince(Grid province) {
        this.province = province;
    }

    public Grid getCity() {
        return city;
    }

    public void setCity(Grid city) {
        this.city = city;
    }

    public Grid getTown() {
        return town;
    }

    public void setTown(Grid town) {
        this.town = town;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }
    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", pId=" + pId +
                ", cId=" + cId +
                ", tId=" + tId +
                ", state=" + state +
                ", avatar='" + avatar + '\'' +
                ", roleId=" + roleId +
                ", province=" + province +
                ", city=" + city +
                ", town=" + town +
                ", token='" + token + '\'' +
                '}';
    }
}

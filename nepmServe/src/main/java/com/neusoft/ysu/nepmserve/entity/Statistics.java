package com.neusoft.ysu.nepmserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pId;

    private Integer cId;

    private Integer tId;

    private String address;

    /**
     * so2实测浓度
     */
    private Integer so2Value;

    /**
     * so2指数级别
     */
    private Integer so2Level;

    /**
     * co实测浓度
     */
    private Integer coValue;

    /**
     * co指数级别
     */
    private Integer coLevel;

    /**
     * 实测悬浮颗粒物浓度
     */
    private Integer spmValue;

    /**
     * pm2.5指数级别
     */
    private Integer spmLevel;

    /**
     * 空气质量指数级别
     */
    private Integer aqiId;

    /**
     * 确认日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDate confirmDate;

    /**
     * 确认时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
    private LocalTime confirmTime;

    private Integer gId;

    private Integer sId;

    private String info;

    private Integer fId;

    @TableField(exist = false)
    private User gridman;

    @TableField(exist = false)
    private User supervisor;

    @TableField(exist = false)
    private Grid province;

    @TableField(exist = false)
    private Grid city;

    @TableField(exist = false)
    private Grid town;

    public User getGridman() {
        return gridman;
    }

    public void setGridman(User gridman) {
        this.gridman = gridman;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getSo2Value() {
        return so2Value;
    }

    public void setSo2Value(Integer so2Value) {
        this.so2Value = so2Value;
    }
    public Integer getSo2Level() {
        return so2Level;
    }

    public void setSo2Level(Integer so2Level) {
        this.so2Level = so2Level;
    }
    public Integer getCoValue() {
        return coValue;
    }

    public void setCoValue(Integer coValue) {
        this.coValue = coValue;
    }
    public Integer getCoLevel() {
        return coLevel;
    }

    public void setCoLevel(Integer coLevel) {
        this.coLevel = coLevel;
    }
    public Integer getSpmValue() {
        return spmValue;
    }

    public void setSpmValue(Integer spmValue) {
        this.spmValue = spmValue;
    }
    public Integer getSpmLevel() {
        return spmLevel;
    }

    public void setSpmLevel(Integer spmLevel) {
        this.spmLevel = spmLevel;
    }
    public Integer getAqiId() {
        return aqiId;
    }

    public void setAqiId(Integer aqiId) {
        this.aqiId = aqiId;
    }
    public LocalDate getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDate confirmDate) {
        this.confirmDate = confirmDate;
    }
    public LocalTime getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(LocalTime confirmTime) {
        this.confirmTime = confirmTime;
    }
    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }
    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", pId=" + pId +
                ", cId=" + cId +
                ", tId=" + tId +
                ", address='" + address + '\'' +
                ", so2Value=" + so2Value +
                ", so2Level=" + so2Level +
                ", coValue=" + coValue +
                ", coLevel=" + coLevel +
                ", spmValue=" + spmValue +
                ", spmLevel=" + spmLevel +
                ", aqiId=" + aqiId +
                ", confirmDate=" + confirmDate +
                ", confirmTime=" + confirmTime +
                ", gId=" + gId +
                ", sId=" + sId +
                ", info='" + info + '\'' +
                ", fId=" + fId +
                ", gridman=" + gridman +
                ", supervisor=" + supervisor +
                ", province=" + province +
                ", city=" + city +
                ", town=" + town +
                '}';
    }
}

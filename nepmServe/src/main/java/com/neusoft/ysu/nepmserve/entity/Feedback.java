package com.neusoft.ysu.nepmserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-25
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公共监督员编号
     */
    private Integer sId;

    /**
     * 反馈信息所在省编号
     */
    private Integer pId;

    /**
     * 所在市区编号
     */
    private Integer cId;

    /**
     * 所在县（区）编号
     */
    private Integer tId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 反馈信息描述
     */
    private String info;

    /**
     * 预估等级
     */
    private Integer estimatedGrade;

    /**
     * 反馈日期
     */
//    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDate fDate;

    /**
     * 反馈时间
     */
//    @Getter
    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
    private LocalTime fTime;

    /**
     * 指派的网格员编号
     */
    private Integer gId;

    /**
     * 指派日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDate assignDate;

    /**
     * 指派时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
    private LocalTime assignTime;

    /**
     * 信息状态：	0：未指派，	1：已指派，	2：已确认
     */
    private Integer state;

    @TableField(exist = false)
    private Aqi aqi;

    @TableField(exist = false)
    private User grid;

    @TableField(exist = false)
    private User supervisor;

    @TableField(exist = false)
    private Grid province;

    @TableField(exist = false)
    private Grid city;

    @TableField(exist = false)
    private Grid town;

    @TableField(exist = false)
    private Statistics statistics;

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public User getGrid() {
        return grid;
    }

    public void setGrid(User grid) {
        this.grid = grid;
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
    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
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
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public Integer getEstimatedGrade() {
        return estimatedGrade;
    }

    public void setEstimatedGrade(Integer estimatedGrade) {
        this.estimatedGrade = estimatedGrade;
    }

    public LocalDate getfDate() {
        return fDate;
    }

    public void setfDate(LocalDate fDate) {
        this.fDate = fDate;
    }

    public LocalTime getfTime() {
        return fTime;
    }

    public void setfTime(LocalTime fTime) {
        this.fTime = fTime;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
    }

    public LocalTime getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(LocalTime assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", sId=" + sId +
                ", pId=" + pId +
                ", cId=" + cId +
                ", tId=" + tId +
                ", address='" + address + '\'' +
                ", info='" + info + '\'' +
                ", estimatedGrade=" + estimatedGrade +
                ", fDate=" + fDate +
                ", fTime=" + fTime +
                ", gId=" + gId +
                ", assignDate=" + assignDate +
                ", assignTime=" + assignTime +
                ", state=" + state +
                ", aqi=" + aqi +
                ", grid=" + grid +
                ", supervisor=" + supervisor +
                ", province=" + province +
                ", city=" + city +
                ", town=" + town +
                ", statistics=" + statistics +
                '}';
    }
}

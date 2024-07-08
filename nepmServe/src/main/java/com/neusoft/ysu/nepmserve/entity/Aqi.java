package com.neusoft.ysu.nepmserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-24
 */
public class Aqi implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 空气质量指数级别
     */
    private String level;

    /**
     * 空气质量指数类别
     */
    private String category;

    /**
     * 空气质量指数级别描述
     */
    private String aqiExplain;

    /**
     * 表示颜色
     */
    private String color;

    /**
     * 健康影响情况
     */
    private String impact;

    /**
     * 采取的建议
     */
    private String step;

    private Integer so2Min;

    private Integer so2Max;

    private Integer coMin;

    private Integer coMax;

    private Integer spmMin;

    private Integer spmMax;

    private Integer iaqiMin;

    private Integer iaqiMax;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getAqiExplain() {
        return aqiExplain;
    }

    public void setAqiExplain(String aqiExplain) {
        this.aqiExplain = aqiExplain;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
    public Integer getSo2Min() {
        return so2Min;
    }

    public void setSo2Min(Integer so2Min) {
        this.so2Min = so2Min;
    }
    public Integer getSo2Max() {
        return so2Max;
    }

    public void setSo2Max(Integer so2Max) {
        this.so2Max = so2Max;
    }
    public Integer getCoMin() {
        return coMin;
    }

    public void setCoMin(Integer coMin) {
        this.coMin = coMin;
    }
    public Integer getCoMax() {
        return coMax;
    }

    public void setCoMax(Integer coMax) {
        this.coMax = coMax;
    }
    public Integer getSpmMin() {
        return spmMin;
    }

    public void setSpmMin(Integer spmMin) {
        this.spmMin = spmMin;
    }
    public Integer getSpmMax() {
        return spmMax;
    }

    public Integer getIaqiMin() {
        return iaqiMin;
    }

    public void setIaqiMin(Integer iaqiMin) {
        this.iaqiMin = iaqiMin;
    }

    public Integer getIaqiMax() {
        return iaqiMax;
    }

    public void setIaqiMax(Integer iaqiMax) {
        this.iaqiMax = iaqiMax;
    }

    public void setSpmMax(Integer spmMax) {
        this.spmMax = spmMax;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Aqi{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", category='" + category + '\'' +
                ", aqiExplain='" + aqiExplain + '\'' +
                ", color='" + color + '\'' +
                ", impact='" + impact + '\'' +
                ", step='" + step + '\'' +
                ", so2Min=" + so2Min +
                ", so2Max=" + so2Max +
                ", coMin=" + coMin +
                ", coMax=" + coMax +
                ", spmMin=" + spmMin +
                ", spmMax=" + spmMax +
                ", iaqiMin=" + iaqiMin +
                ", iaqiMax=" + iaqiMax +
                ", remark='" + remark + '\'' +
                '}';
    }
}

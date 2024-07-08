package com.neusoft.ysu.nepmserve.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class StatisticsVo extends PageVo{
    private Integer pId;

    private Integer cId;
    private Integer tId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDate confirmDate;

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

    public LocalDate getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDate confirmDate) {
        this.confirmDate = confirmDate;
    }
}

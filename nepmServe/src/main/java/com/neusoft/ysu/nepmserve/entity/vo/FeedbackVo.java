package com.neusoft.ysu.nepmserve.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class FeedbackVo extends PageVo{
    private Integer tId;
    private Integer estimatedGrade;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDate fDate;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
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
}

package com.microservice.weather.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 10:31
 */

@Data
public class Yesterday implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;

    public Yesterday() {
    }
}

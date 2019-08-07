package com.microservice.weather.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 未来天气信息
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 10:33
 */

@Data
public class Forecast implements Serializable {

    private static final long serialVersionUID = 1L;

    private String date;
    private String high;
    private String fengxiang;
    private String low;
    private String fengli;
    private String type;

    public Forecast() {

    }
}

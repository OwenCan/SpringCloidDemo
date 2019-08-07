package com.microservice.weather.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 天气信息类
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 9:57
 */

@Data
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String api;
    private String wendu;
    private String ganmao;
    private Yesterday yesterday;
    private List<Forecast> forecast;

}

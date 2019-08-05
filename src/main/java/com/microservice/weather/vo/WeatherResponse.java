package com.microservice.weather.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 消息返回
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 10:35
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Weather data;
    private String status;
    private String desc;

}

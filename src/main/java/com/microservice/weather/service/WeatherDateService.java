package com.microservice.weather.service;

import com.microservice.weather.vo.WeatherResponse;

/**
 * @Description: 服务接口
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 10:39
 */
public interface WeatherDateService {

    /**
     * 根据城市ID查询天气数据
     * @param cityId
     * @return
     */
    WeatherResponse getDateByCityId(String cityId);


    /**
     * 根据城市名称获取天气数据
     * @param name
     * @return
     */
    WeatherResponse getDateByCityName(String name);
}

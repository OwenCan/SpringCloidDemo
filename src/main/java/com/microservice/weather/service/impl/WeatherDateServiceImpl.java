package com.microservice.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.weather.service.WeatherDateService;
import com.microservice.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @Description: 接口实现类
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 10:42
 */
@Service
public class WeatherDateServiceImpl implements WeatherDateService {

    @Autowired
    private RestTemplate restTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

    @Override
    public WeatherResponse getDateByCityId(String cityId) {
        String uri = WEATHER_API + "?citykey=" + cityId;
        return this.doGetWeatherDate(uri);
    }

    /**
     * 获取天气数据
     *
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeatherDate(String uri) {
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        String strBody = null;

        //如果状态为200 说明值正确
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        }

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weather = null;

        try {
            weather = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return weather;
    }

    @Override
    public WeatherResponse getDateByCityName(String cityName) {
        String uri = WEATHER_API + "?city=" + cityName;
        return this.doGetWeatherDate(uri);
    }
}

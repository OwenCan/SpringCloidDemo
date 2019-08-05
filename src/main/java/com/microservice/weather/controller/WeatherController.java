package com.microservice.weather.controller;

import com.microservice.weather.service.WeatherDateService;
import com.microservice.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 天气Controller
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 11:05
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherDateService weatherDateService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId) {
        return weatherDateService.getDateByCityId(cityId);
    }

    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getReportByCityName(@PathVariable("cityName") String cityName) {
        return weatherDateService.getDateByCityName(cityName);
    }

}

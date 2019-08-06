package com.microservice.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.weather.service.WeatherDateService;
import com.microservice.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 接口实现类
 * @Author: 赵广来
 * @CreateDate: 2019/8/5 10:42
 */
@Service
public class WeatherDateServiceImpl implements WeatherDateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

    @Override
    public WeatherResponse getDateByCityId(String cityId) {
        String uri = WEATHER_API + "?citykey=" + cityId;
        return this.doGetWeatherDate(uri);
    }

    @Override
    public WeatherResponse getDateByCityName(String cityName) {
        String uri = WEATHER_API + "?city=" + cityName;
        return this.doGetWeatherDate(uri);
    }

    /**
     * 获取天气数据Version2.0
     * 增加了 StringRedisTemplate 用于操作 Redis。
     *
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeatherDate(String uri) {
        ValueOperations<String, String> ops =
                this.stringRedisTemplate.opsForValue();
        String key = uri;
        WeatherResponse weather = null;
        String strBody = null;

        //当不存在某个key值的时候，从去天气接口里面取最新的数据，并初始化到Redis中
        if (!this.stringRedisTemplate.hasKey(key)) {
            System.out.println("Not found key" + key);
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

            if (response.getStatusCodeValue() == 200) {
                strBody = response.getBody();
            }

            ops.set(key, strBody, 10L, TimeUnit.SECONDS);
        } else {
            //当存在某个key时，我们就从resid里面拿数据
            System.out.println("Found key" + key + ",value=" + ops.get(key));
            strBody = ops.get(key);
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            weather = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return weather;
    }

    //获取天气数据 Version1.0，直接获取接口数据
    /*private WeatherResponse doGetWeatherDate(String uri) {
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
    }*/


}

package com.samar.journalApp.service;

import com.samar.journalApp.api.response.WeatherResponse;
import com.samar.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCheck {
    @Value("${weather.api.key}")
    private String APIKEY;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalUrl=appCache.APP_CACHE.get("weather_api").replace("<city>",city).replace("<apiKey>",APIKEY);
        ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalUrl, HttpMethod.GET,null, WeatherResponse.class);
        return response.getBody();
    }
}

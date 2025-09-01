package com.prateek.journalApp.service;

import com.prateek.journalApp.api_response.WeatherResponse;
import com.prateek.journalApp.cache.AppCache;
import com.prateek.journalApp.placeholders.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(appCache.APP_CACHE.get(AppCache.keys.WEATHER_API_URL.toString()))
                .queryParam(Placeholders.API_KEY, apiKey)
                .queryParam(Placeholders.QUERY, city)
                .queryParam(Placeholders.AQI, "no");

        URI url = builder.build(true).toUri();

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}

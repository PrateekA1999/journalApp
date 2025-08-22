package com.prateek.journalApp.controller;

import com.prateek.journalApp.api_response.WeatherResponse;
import com.prateek.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}/temperature")
    public ResponseEntity<String> getTemperatureByCity(@PathVariable String city, @RequestParam(value = "unit", required = false, defaultValue = "c") String unit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        String tempratureStatement = "";
        WeatherResponse response = weatherService.getWeather(city);
        if (response != null) {
            tempratureStatement = unit.equals("c") ? ". The weather in " + city + " is " + response.getCurrent().getTempC() + "°C and feels like " + response.getCurrent().getFeelsLikeC() + "°C." : ". The weather in " + city + " is " + response.getCurrent().getTempF() + "°F and feels like " + response.getCurrent().getFeelsLikeF() + "°F.";
        }
        return new ResponseEntity<>("Hello " + userName + tempratureStatement, HttpStatus.OK);
    }
}

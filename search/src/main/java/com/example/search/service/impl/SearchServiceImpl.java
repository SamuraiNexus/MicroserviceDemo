package com.example.search.service.impl;


import com.example.hibernatedemo.pojo.entity.Employee;
import com.example.search.pojo.dto.ResponseDTO;
import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImpl implements SearchService {

    private final RestTemplate restTemplate;
    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate =restTemplate;
    }
    @Override
    @HystrixCommand(fallbackMethod = "findEmployeeDetails_fallbackHandler" , commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "20"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "10000"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50")
    })
    public ResponseDTO findEmployeeDetails() {

        ResponseDTO responseDTO = CompletableFuture.supplyAsync(() -> {
            Employee[] e = restTemplate.getForEntity(
                    "http://employee/employees", Employee[].class).getBody();
            String details = restTemplate.getForEntity(
                    "http://details/details/port", String.class).getBody();
            ResponseDTO res = new ResponseDTO();
            res.setEmployee(e);
            res.setDetailsMessage(details);
            return res;
        }).join();
        return responseDTO;
    }

    public ResponseDTO findEmployeeDetails_fallbackHandler() {
        return new ResponseDTO();
    }
}

package com.acf.examples.java17.service;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.user.BreedRec;

import com.acf.examples.java17.record.user.DetailsForBreedResponse;
import com.acf.examples.java17.record.user.User;
import java.util.UUID;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RandomUserService {

    RestTemplate rtRestClient;

    private String fullUrl;

    @Autowired
    public RandomUserService(RestTemplate rt, @Value("${services.randomuser.fullUrl}") String forUrl) {
        rtRestClient = rt;
        fullUrl = forUrl;
    }


    public ServiceResponse<User> getRandomUser() {

        ServiceResponse<User> results = rtRestClient.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<ServiceResponse<User>>() { }
                )
                .getBody();

        return results;

    }

    @Async
    public CompletableFuture<ServiceResponse<User>> getUserForCity(String whichCity) {

        // This is just to set the request headers
        String queryUrl = fullUrl + "?city=" + whichCity;

        ServiceResponse<User> results = rtRestClient.exchange(
                        queryUrl,
                        HttpMethod.GET,
                        // requestEntity is just so we can set headers and not passing anything of value to the request
                        RequestEntity.EMPTY,
                        new ParameterizedTypeReference<ServiceResponse<User>>() { } )
                .getBody();

        String logStr = String.format("Executing fetch for city: %s in ThreadId: %s", whichCity, Thread.currentThread().getId());
        log.info(logStr);

        return CompletableFuture.completedFuture(results);

    }

}

package com.acf.examples.java17.service;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.user.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
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


    public ServiceResponse<List<User>> getRandomUser() {

        ServiceResponse<List<User>> results = rtRestClient.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<ServiceResponse<List<User>>>() { }
                )
                .getBody();

        return results;

    }

    @Async
    public CompletableFuture<ServiceResponse<List<User>>> getUserForCity(String whichCity) {

        // This is just to set the request headers
        String queryUrl = fullUrl + "?city=" + whichCity;

        ServiceResponse<List<User>> results = rtRestClient.exchange(
                        queryUrl,
                        HttpMethod.GET,
                        RequestEntity.EMPTY,
                        new ParameterizedTypeReference<ServiceResponse<List<User>>>() { } )
                .getBody();

        String logStr = String.format("Executing fetch for city: %s in ThreadId: %s", whichCity, Thread.currentThread().getId());
        log.info(logStr);

        return CompletableFuture.completedFuture(results);

    }

}

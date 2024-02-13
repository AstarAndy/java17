package com.acf.examples.java17.service;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.breed.BreedRec;

import com.acf.examples.java17.record.breed.DetailsForBreedResponse;
import java.util.UUID;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DogsApiService {

    @Autowired
    RestTemplate rtRestClient;

    @Value("${services.dogapi.breeds.fullUrl}")
    private String breedsFullUrl;


    public DetailsForBreedResponse getAllBreeds() {

        DetailsForBreedResponse results = rtRestClient.getForObject(breedsFullUrl, DetailsForBreedResponse.class);

        return results;

    }

    @Async
    public CompletableFuture<ServiceResponse<BreedRec>> getBreedForUuid(UUID breedId) {

        // This is just to set the request headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        String fullUrl = breedsFullUrl + "/" + breedId;

        ServiceResponse<BreedRec> results = rtRestClient.exchange(
                        fullUrl,
                        HttpMethod.GET,
                        // requestEntity is just so we can set headers and not passing anything of value to the request
                        requestEntity,
                        new ParameterizedTypeReference<ServiceResponse<BreedRec>>() { } )
                .getBody();

        String logStr = String.format("Executing fetch for breedId: %s in ThreadId: %s", breedId, Thread.currentThread().getId());
        log.info(logStr);

        return CompletableFuture.completedFuture(results);

    }


}

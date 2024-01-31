package com.acf.examples.java17.service;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.breed.BreedRec;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogsApiService {

    @Autowired
    RestTemplate rtRestClient;

    @Value("${services.dogapi.breeds.fullUrl}")
    private String breedsFullUrl;

    public ServiceResponse<List<BreedRec>> getAllBreeds() {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        // This is just to set the request headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        return rtRestClient.exchange(
                breedsFullUrl,
                HttpMethod.GET,
                // requestEntity is just so we can set headers and not passing anything of value to the request
                requestEntity,
                new ParameterizedTypeReference<ServiceResponse<List<BreedRec>>>() { } )
                .getBody();

    }

}

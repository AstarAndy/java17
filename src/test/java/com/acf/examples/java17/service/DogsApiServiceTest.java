package com.acf.examples.java17.service;

import com.acf.examples.java17.record.breed.BreedRec;
import com.acf.examples.java17.record.breed.BreedsListResponse;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogsApiServiceTest {

    @Mock
    RestTemplate rtRestClient;

    @InjectMocks
    private DogsApiService dogsApi = new DogsApiService();

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllBreedsTest() {

        //ServiceResponse<List<BreedRec>> mockedResult = new ServiceResponse<>(new ArrayList<>());
        BreedsListResponse mockedResult = new BreedsListResponse(new ArrayList<BreedRec>());

        /*
                The service being tested, DogsApiService has a private
                field, breedsFullUrl, that gets injected by the @Value
                annotation.  This in this test that field is null you
                can inject a value Using RelectionUtils, or use the
                Mockito.ArgumentMatchers.isNull() OR
                you can use ReflectionUtils
                ReflectionTestUtils.setField(dogsApi, "breedsFullUrl", "/mocked/url/value");
         */

        // Remember, when mocking, all params must be actual values or all matchers
        ReflectionTestUtils.setField(dogsApi, "breedsFullUrl", "/mocked/url/value");
        when(rtRestClient
             .getForObject("/mocked/url/value", BreedsListResponse.class))
            .thenReturn(mockedResult);


        BreedsListResponse actualResult = dogsApi.getAllBreeds();
        Assert.assertEquals(mockedResult, actualResult);

    }

    @Test
    void getBreedForUuid() {
    }
}
package com.acf.examples.java17.service;

import com.acf.examples.java17.record.user.BreedRec;
import com.acf.examples.java17.record.user.DetailsForBreedResponse;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class DogsApiServiceTest {

    @Mock
    RestTemplate rtRestClient;

    @InjectMocks
    private RandomUserService dogsApi = new RandomUserService(rtRestClient, "");

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllBreedsTest() {

        //ServiceResponse<List<BreedRec>> mockedResult = new ServiceResponse<>(new ArrayList<>());
        DetailsForBreedResponse mockedResult = new DetailsForBreedResponse(new ArrayList<BreedRec>());

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
             .getForObject("/mocked/url/value", DetailsForBreedResponse.class))
            .thenReturn(mockedResult);


        DetailsForBreedResponse actualResult = dogsApi.getAllBreeds();
        Assert.assertEquals(mockedResult, actualResult);

    }

    @Test
    void getBreedForUuid() {
    }
}
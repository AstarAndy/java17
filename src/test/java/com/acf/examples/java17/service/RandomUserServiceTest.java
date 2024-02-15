package com.acf.examples.java17.service;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.user.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class RandomUserServiceTest {

    @Mock
    RestTemplate rtRestClient;

    @InjectMocks
    private RandomUserService userService = new RandomUserService(rtRestClient, "");

    @BeforeEach
    void setUp() {

    }

    @Test
    void getRandomUserTest() {
        ServiceResponse<List<User>> mockedBody = new ServiceResponse<>(new ArrayList<>());
        ResponseEntity<ServiceResponse<List<User>>> mockedResponse = ResponseEntity.ok(mockedBody);

        when (rtRestClient.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(ResponseEntity.EMPTY),
                eq(new ParameterizedTypeReference<ServiceResponse<List<User>>>() { } ))
        )
        .thenReturn(mockedResponse);  // RestTemplate.exchange returns a ResponseEntity

        var mockedResult = mockedResponse.getBody();    // ServiceResponse<List<User>>
        var actualResult = userService.getRandomUser(); // ServiceResponse<List<User>>

        Assert.assertEquals(mockedResult, actualResult);

    }

}
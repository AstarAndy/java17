package com.acf.examples.java17.controller;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.user.User;
import com.acf.examples.java17.service.RandomUserService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@Slf4j
public class RandomUserApiController {

    @Autowired
    private RandomUserService userService;
    @GetMapping(value = "")
    public ResponseEntity<ServiceResponse<List<User>>> getUser(@RequestParam Optional<String> city) {
        var result = city.map(this::getUserForCity).orElseGet(this::getRandomUser);
        return result;
    }

    private ResponseEntity<ServiceResponse<List<User>>> getRandomUser() {
        var result = userService.getRandomUser();
        log.info("Response from randomUser is \n\n" + result);
        return ResponseEntity.ok(result);
    }
    private ResponseEntity<ServiceResponse<List<User>>> getUserForCity(String someCity) {

        String logStr = String.format("Executing fetch for someCity: %s in ThreadId: %s", someCity, Thread.currentThread().getId());
        log.info(logStr);

        var result = userService.getUserForCity(someCity);

        logStr = String.format("Completed fetch for someCity: %s in ThreadId: %s", someCity, Thread.currentThread().getId());
        log.info(logStr);

        try {
            return ResponseEntity.ok(result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

}

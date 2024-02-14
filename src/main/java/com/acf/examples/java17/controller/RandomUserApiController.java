package com.acf.examples.java17.controller;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.user.BreedRec;
import com.acf.examples.java17.record.user.DetailsForBreedResponse;
import com.acf.examples.java17.record.user.User;
import com.acf.examples.java17.service.RandomUserService;
import java.util.UUID;
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
    @GetMapping(value = "/api")
    public ResponseEntity<ServiceResponse<User>> getRandomPerson() {
        var result = userService.getRandomUser();
        log.info("Response from randomPerson is \n\n" + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/api")
    public ResponseEntity<ServiceResponse<User>> geBreedDetails(@RequestParam String someCity) {

        String logStr = String.format("Executing fetch for someCity: %s in ThreadId: %s", someCity, Thread.currentThread().getId());
        log.info(logStr);

        CompletableFuture<ServiceResponse<User>> result = userService.getUserForCity(someCity);

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

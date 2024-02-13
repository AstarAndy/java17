package com.acf.examples.java17.controller;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.breed.BreedRec;
import com.acf.examples.java17.record.breed.DetailsForBreedResponse;
import com.acf.examples.java17.service.DogsApiService;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2")
@Slf4j
public class DogsApiController {

    @Autowired
    private DogsApiService dogsService;
    @GetMapping(value = "/breeds")
    public ResponseEntity<DetailsForBreedResponse> getAllBreeds() {
        DetailsForBreedResponse result = dogsService.getAllBreeds();
        log.info("Response from getAllBreeds is \n\n" + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/breeds/{breedId}")
    public ResponseEntity<ServiceResponse<BreedRec>> geBreedDetails(@PathVariable("breedId") UUID breedId) {

        String logStr = String.format("Executing fetch for breedId: %s in ThreadId: %s", breedId, Thread.currentThread().getId());
        log.info(logStr);

        CompletableFuture<ServiceResponse<BreedRec>> result = dogsService.getBreedForUuid(breedId);

        logStr = String.format("Completed fetch for breedId: %s in ThreadId: %s", breedId, Thread.currentThread().getId());
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

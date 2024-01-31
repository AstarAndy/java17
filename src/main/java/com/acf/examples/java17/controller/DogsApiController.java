package com.acf.examples.java17.controller;

import com.acf.examples.java17.record.ServiceResponse;
import com.acf.examples.java17.record.breed.BreedRec;
import com.acf.examples.java17.service.DogsApiService;
import java.util.List;
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
    public ResponseEntity<ServiceResponse<List<BreedRec>>> getAllBreeds() {
        ServiceResponse<List<BreedRec>> result = dogsService.getAllBreeds();
        log.info("Response from getAllBreeds is \n\n" + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/breeds/{breedId}")
    public ResponseEntity<String> geBreedDetails(@PathVariable("breedId") Integer breedId) {
        return ResponseEntity.ok("Here is your stuff from /breeds/{breedId}" + breedId);
    }

}

package com.example.simpleapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SimpleController {
    @PostMapping(value = "/**", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pingPong(HttpEntity<String> requestEntity, @RequestParam Optional<Integer> sleepMillis,
                                           @RequestParam Optional<Integer> responseStatus) throws InterruptedException {
        if (sleepMillis.isPresent()) {
            Thread.sleep(sleepMillis.get());
        }
        return ResponseEntity.status(responseStatus.orElse(HttpStatus.OK.value()))
                .contentType(MediaType.APPLICATION_JSON).body(requestEntity.getBody());
    }
}

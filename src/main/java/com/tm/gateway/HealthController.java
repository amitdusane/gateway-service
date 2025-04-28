package com.tm.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.info("In health endpoint... ");
        return ResponseEntity.ok("Spring Cloud Gateway is up and running");
    }
}
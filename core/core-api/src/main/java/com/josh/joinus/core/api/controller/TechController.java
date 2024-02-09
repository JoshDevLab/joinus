package com.josh.joinus.core.api.controller;

import com.josh.joinus.core.api.TechRequest;
import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.TechService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tech")
@RequiredArgsConstructor
public class TechController {

    private final TechService techService;

    @PostMapping("/add")
    public ResponseEntity<TechResponse> add(@RequestBody TechRequest techRequest) {
        TechResponse techResponse = TechResponse.create(techService.add(techRequest.getName()));
        return ResponseEntity.ok(techResponse);
    }
}

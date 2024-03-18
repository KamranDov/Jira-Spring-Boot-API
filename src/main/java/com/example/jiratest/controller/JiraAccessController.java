package com.example.jiratest.controller;

import com.example.jiratest.domain.JiraPayload;
import com.example.jiratest.service.JiraAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jira")
@RequiredArgsConstructor
public class JiraAccessController {

    private final JiraAccessService jiraAccessService;

    @PostMapping("/test")
    public ResponseEntity<String> access(@RequestBody JiraPayload jiraPayload) {
        jiraAccessService.accessJira(jiraPayload);
        return new ResponseEntity<>(jiraAccessService.accessJira(jiraPayload), HttpStatus.NO_CONTENT);
    }
}

package com.example.jira.controller;

import com.example.jira.service.JiraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jira")
public class JiraController {

    private final JiraService jiraService;

    public JiraController(JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @GetMapping("/combinedTickets")
    public ResponseEntity<List<Map<String, Object>>> getCombinedTickets() {
        try {
            List<Map<String, Object>> tickets = jiraService.getCombinedTickets();

            if (tickets.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(tickets); // 200 OK
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(null); // 502 Bad Gateway (JIRA API error)
        }
    }
}


package com.example.jira.controller;

import com.example.jira.service.JiraService;
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
    public List<Map<String, Object>> getCombinedTickets() throws IOException {
        return jiraService.getCombinedTickets();
    }
}


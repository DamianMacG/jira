package com.example.jira.util;

import com.example.jira.model.JiraTicketA;
import com.example.jira.model.JiraTicketB;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.util.List;

public class JsonFileLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()); // Ensure JavaTimeModule is registered

    public static List<JiraTicketA> loadJiraTicketsA() throws IOException {
        return List.of(objectMapper.readValue(
                new ClassPathResource("jira_a.json").getInputStream(),
                JiraTicketA[].class
        ));
    }

    public static List<JiraTicketB> loadJiraTicketsB() throws IOException {
        return List.of(objectMapper.readValue(
                new ClassPathResource("jira_b.json").getInputStream(),
                JiraTicketB[].class
        ));
    }
}


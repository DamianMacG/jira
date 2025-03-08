package com.example.jira.service;

import com.example.jira.model.JiraTicketA;
import com.example.jira.model.JiraTicketB;
import com.example.jira.repository.JiraRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class JiraService {

    private final JiraRepository jiraRepository;

    public JiraService(JiraRepository jiraRepository) {
        this.jiraRepository = jiraRepository;
    }

    public List<Map<String, Object>> getCombinedTickets() throws IOException {
        List<JiraTicketA> ticketsA = jiraRepository.fetchJiraTicketsA();
        List<JiraTicketB> ticketsB = jiraRepository.fetchJiraTicketsB();

        // **One shared map to store ALL merged ticket data**
        Map<String, Map<String, Object>> combinedMap = new HashMap<>();

        // **Step 1: Add JiraTicketA data**
        for (JiraTicketA ticketA : ticketsA) {
            Map<String, Object> data = new HashMap<>();

            // Add ALL JiraTicketA fields
            data.put("idA", ticketA.getId());
            data.put("epicName", ticketA.getEpicName());
            data.put("arpm", ticketA.getArpm());
            data.put("readinessStatus", ticketA.getReadinessStatus());
            data.put("t1", ticketA.getT1());
            data.put("t2", ticketA.getT2());
            data.put("t3", ticketA.getT3());

            // Initialize lastUpdated with JiraTicketA timestamp
            data.put("lastUpdated", ticketA.getLastUpdated());

            combinedMap.put(ticketA.getId(), data);
        }

        // **Step 2: Add JiraTicketB data**
        for (JiraTicketB ticketB : ticketsB) {
            // If ticketA already exists, merge it, otherwise create a new entry
            Map<String, Object> data = combinedMap.getOrDefault(ticketB.getId(), new HashMap<>());

            // Add ALL JiraTicketB fields
            data.put("idB", ticketB.getId());
            data.put("projectName", ticketB.getProjectName());
            data.put("priority", ticketB.getPriority());
            data.put("dueDate", ticketB.getDueDate());
            data.put("assignee", ticketB.getAssignee());
            data.put("component", ticketB.getComponent());

            // **Step 3: Ensure lastUpdated always holds the latest timestamp**
            if (data.containsKey("lastUpdated")) {
                LocalDateTime existingLastUpdated = (LocalDateTime) data.get("lastUpdated");
                data.put("lastUpdated", existingLastUpdated.isAfter(ticketB.getLastUpdated())
                        ? existingLastUpdated
                        : ticketB.getLastUpdated());
            } else {
                data.put("lastUpdated", ticketB.getLastUpdated());
            }

            combinedMap.put(ticketB.getId(), data);
        }

        // Convert map values into a list
        return new ArrayList<>(combinedMap.values());
    }
}


package com.example.jira.service;

import com.example.jira.model.JiraTicketA;
import com.example.jira.model.JiraTicketB;
import com.example.jira.util.JsonFileLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class JiraService {

    public List<JiraTicketA> getJiraTicketsA() throws IOException {
        return JsonFileLoader.loadJiraTicketsA();
    }

    public List<JiraTicketB> getJiraTicketsB() throws IOException {
        return JsonFileLoader.loadJiraTicketsB();
    }

    public List<Map<String, Object>> getCombinedTickets() throws IOException {
        List<JiraTicketA> ticketsA = getJiraTicketsA();
        List<JiraTicketB> ticketsB = getJiraTicketsB();

        Map<String, Map<String, Object>> combinedMap = new HashMap<>();

        // Add JiraTicketA data to the map
        for (JiraTicketA ticketA : ticketsA) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", ticketA.getId());
            data.put("epicName", ticketA.getEpicName());
            data.put("arpm", ticketA.getArpm());
            data.put("readinessStatus", ticketA.getReadinessStatus());
            data.put("t1", ticketA.getT1());
            data.put("t2", ticketA.getT2());
            data.put("t3", ticketA.getT3());
            data.put("lastUpdated", ticketA.getLastUpdated());

            combinedMap.put(ticketA.getId(), data);
        }

        // Add JiraTicketB data to the map
        for (JiraTicketB ticketB : ticketsB) {
            Map<String, Object> data = combinedMap.getOrDefault(ticketB.getId(), new HashMap<>());
            data.put("id", ticketB.getId());
            data.put("projectName", ticketB.getProjectName());
            data.put("priority", ticketB.getPriority());
            data.put("dueDate", ticketB.getDueDate());
            data.put("assignee", ticketB.getAssignee());
            data.put("component", ticketB.getComponent());

            // Set lastUpdated to the latest timestamp
            if (data.containsKey("lastUpdated")) {
                LocalDateTime existingLastUpdated = (LocalDateTime) data.get("lastUpdated");
                data.put("lastUpdated", existingLastUpdated.isAfter(ticketB.getLastUpdated()) ? existingLastUpdated : ticketB.getLastUpdated());
            } else {
                data.put("lastUpdated", ticketB.getLastUpdated());
            }

            combinedMap.put(ticketB.getId(), data);
        }

        return new ArrayList<>(combinedMap.values());
    }
}


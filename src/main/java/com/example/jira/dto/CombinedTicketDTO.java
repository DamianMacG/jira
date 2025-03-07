package com.example.jira.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CombinedTicketDTO {
    private String id;

    // Fields from JiraTicketA
    private String epicName;
    private String arpm;
    private String readinessStatus;
    private String t1;
    private String t2;
    private String t3;

    // Fields from JiraTicketB
    private String projectName;
    private String priority;
    private String dueDate;
    private String assignee;
    private String component;

    // Last updated timestamp (whichever is newer)
    private LocalDateTime lastUpdated;
}

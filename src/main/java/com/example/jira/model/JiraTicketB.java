package com.example.jira.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraTicketB {
    @Id
    private String id;
    private String projectName;
    private String priority;
    private String dueDate;
    private String assignee;
    private String component;
    private LocalDateTime lastUpdated;
}
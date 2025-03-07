package com.example.jira.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraTicketA {
    @Id
    private String id;
    private String epicName;
    private String arpm;
    private String readinessStatus;
    private String t1;
    private String t2;
    private String t3;
    private LocalDateTime lastUpdated;
}

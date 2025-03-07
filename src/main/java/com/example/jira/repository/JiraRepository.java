package com.example.jira.repository;

import com.example.jira.model.JiraTicketA;
import com.example.jira.model.JiraTicketB;
import com.example.jira.util.JsonFileLoader;
import org.springframework.stereotype.Repository;
//import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

    @Repository
    public class JiraRepository {

        public List<JiraTicketA> fetchJiraTicketsA() throws IOException {
            return JsonFileLoader.loadJiraTicketsA(); // JSON file loading moved here
        }

        public List<JiraTicketB> fetchJiraTicketsB() throws IOException {
            return JsonFileLoader.loadJiraTicketsB(); // JSON file loading moved here
        }
    }

//    private final WebClient webClient;
//
//    public JiraRepository(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://fake-jira-api.com").build();
//    }
//
//    public List<JiraTicketA> fetchJiraTicketsA() {
//        return webClient.get()
//                .uri("/ticketsA")
//                .retrieve()
//                .bodyToMono(JiraTicketA[].class)
//                .map(List::of)
//                .block();
//    }
//
//    public List<JiraTicketB> fetchJiraTicketsB() {
//        return webClient.get()
//                .uri("/ticketsB")
//                .retrieve()
//                .bodyToMono(JiraTicketB[].class)
//                .map(List::of)
//                .block();
//    }
//}

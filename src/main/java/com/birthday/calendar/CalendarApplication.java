package com.birthday.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalendarApplication {
    public static void main(String[] args) {
        // Initializes the engine and launches the server on http://localhost:8080
        SpringApplication.run(CalendarApplication.class, args);
    }
}

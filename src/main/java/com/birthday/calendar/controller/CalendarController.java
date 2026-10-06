package com.birthday.calendar.controller;

import com.birthday.calendar.entity.CalendarDay;
import com.birthday.calendar.repository.CalendarDayRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(
	    origins = "${ALLOWED_ORIGIN:http://localhost:5173}", 
	    allowedHeaders = "*", 
	    allowCredentials = "true"
	) 
public class CalendarController {

    private final CalendarDayRepository repository;
    
    // Explicit countdown tracking threshold
    private final LocalDate COUNTDOWN_START = LocalDate.of(2026, 10, 22);

    public CalendarController(CalendarDayRepository repository) {
        this.repository = repository;
    }

    /**
         * ENDPOINT 1: Fetches the overall calendar unlock status.
         */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getCalendarStatus(
            @RequestParam(value = "mockDate", required = false) String mockDateStr) {
        
        LocalDate targetDate = (mockDateStr != null && !mockDateStr.isEmpty()) 
                ? LocalDate.parse(mockDateStr) 
                : LocalDate.now();
                
        boolean isMocked = (mockDateStr != null && !mockDateStr.isEmpty());

        int unlockedUpTo = 0;
        if (!targetDate.isBefore(COUNTDOWN_START)) {
            long daysBetween = ChronoUnit.DAYS.between(COUNTDOWN_START, targetDate);
            unlockedUpTo = Math.min(10, Math.max(0, (int) daysBetween + 1));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("realServerDate", LocalDate.now().toString());
        response.put("evaluatedDate", targetDate.toString());
        response.put("unlockedUpToDay", unlockedUpTo);
        response.put("timeTravelActive", isMocked);

        return ResponseEntity.ok(response);
    }

    /**
         * ENDPOINT 2: Fetches the specific minigame details for a single box.
         */
    @GetMapping("/day/{dayNumber}")
    public ResponseEntity<?> getDayDetails(
            @PathVariable int dayNumber,
            @RequestParam(value = "mockDate", required = false) String mockDateStr) {
        
        LocalDate targetDate = (mockDateStr != null && !mockDateStr.isEmpty()) 
                ? LocalDate.parse(mockDateStr) 
                : LocalDate.now();

        int maxAllowedDay = 0;
        if (!targetDate.isBefore(COUNTDOWN_START)) {
            long daysBetween = ChronoUnit.DAYS.between(COUNTDOWN_START, targetDate);
            maxAllowedDay = Math.min(10, Math.max(0, (int) daysBetween + 1));
        }

        // Security Validation Gate
        if (dayNumber > maxAllowedDay || dayNumber < 1) {
            return ResponseEntity.status(403).body("Security Alert: This day is still locked or invalid!");
        }

        Optional<CalendarDay> databaseResult = repository.findById(dayNumber);
        if (databaseResult.isPresent()) {
            return ResponseEntity.ok(databaseResult.get());
        } else {
            return ResponseEntity.status(404).body("Error: Day data not found in MySQL database.");
        }
    }
}


package com.birthday.calendar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "calendar_days")
public class CalendarDay {

    @Id
    @Column(name = "day_number") 
    private int dayNumber; 
    
    private String title;
    
    @Column(name = "minigame_url") 
    private String minigameUrl;

    public CalendarDay() {}

    public CalendarDay(int dayNumber, String title, String minigameUrl) {
        this.dayNumber = dayNumber;
        this.title = title;
        this.minigameUrl = minigameUrl;
    }

    public int getDayNumber() { return dayNumber; }
    public void setDayNumber(int dayNumber) { this.dayNumber = dayNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMinigameUrl() { return minigameUrl; }
    public void setMinigameUrl(String minigameUrl) { this.minigameUrl = minigameUrl; }
}

package com.birthday.calendar.repository;

import com.birthday.calendar.entity.CalendarDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarDayRepository extends JpaRepository<CalendarDay, Integer> {
    
}

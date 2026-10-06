
# Halloween Birthday Countdown - Backend API

This is the Java backend for a custom 10-day countdown calendar built for a special upcoming birthday! 

The countdown officially starts on October 22nd. Every day, a new door opens up to reveal a mini-game. The backend does the heavy lifting here—it checks the real-world server clock to make sure doors unlock on schedule and keeps future days hidden until it's time.

## The Setup
* **Code:** Java 26 + Spring Boot 4
* **Database:** Layerbase (MariaDB)
* **Hosting:** Render (Runs inside a lightweight Docker container)

## Cool Features
* **Timekeeper Endpoints:** Simple routes (`/status` and `/day/{id}`) that calculate exactly which day of the countdown we are on.
* **The "Cheat Code" (Time Travel):** I built a special `mockDate` fallback feature into the API. It forces the server to pretend it’s any date you want, so you can bypass the lock gate and check out any day instantly.


---
Built by **sunnyandkate** | Maharani Websites Portfolio
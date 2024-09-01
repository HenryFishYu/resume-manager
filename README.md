# Resume Manager

A simple web application that allows users to manage and display their resumes. This project provides an intuitive interface for creating, updating, and showcasing resumes online.

## Features

- **User Authentication**: Secure login for users.
- **Resume Management**: Create, update, and delete resumes and select one as their default resume for displaying.
- **Display Resumes**: View resumes in a clean, formatted layout.
- **Export Functionality**: Download resumes in html/PDF format.
- **Responsive Design**: Accessible on both desktop and mobile devices.

## Technologies Used

- **Frontend**: Have not decided yet
- **Backend**: Java, Spring Boot
- **Database**: PostgreSQL
- **Deployment**: Docker-compose

## Getting Started

### For developer

- Java 21
- IntelliJ IDEA
- PostgreSQL
- Docker

1. Docker run PostgreSQL with docker-compose.yml run command: docker-compose up -d
```
# Use postgres/example user/password credentials
version: '3.9'

services:

  db:
    image: postgres
    container_name: postgres
    restart: always
    # set shared memory limit when using docker-compose
    shm_size: 128mb
    # or set shared memory limit when deploy via swarm stack
    volumes:
      - ./data:/var/lib/postgresql/data
    environment:
      POSTGRES_DB: postgres
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
``` 
2. Open the backend folder in IntelliJ IDEA
3. Set environment: spring.profiles.active=dev for ResumeManagerApplication.java
4. Run ResumeManagerApplication.java

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/resume-manager.git
   cd resume-manager
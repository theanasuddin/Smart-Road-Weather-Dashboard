# **Smart Road Weather Dashboard — Backend Service**

Delivering real‑time road, weather, and traffic insights for Finland.

The backend integrates **Digitraffic** and **Finnish Meteorological Institute (FMI)** open data APIs to provide location‑based analytics for the Smart Road Weather Dashboard application.

----------

## **Table of Contents**

-   [Overview]()
-   [Environment Requirements]()
-   [Getting Started]()
    -   [Clone the Repository]()
    -   [Build and Run]()
-   [Swagger UI — API Documentation]()
-   [Project Structure]()
-   [Dependencies]()
-   [Troubleshooting]()

----------

## **Overview**

The backend exposes REST APIs for:

-   Searching locations by city, road name, or coordinates
-   Generating route summaries including:
    -   Road conditions
    -   Weather forecasts
    -   Traffic flow
    -   Hazard alerts
-   Producing time‑based condition timelines

The service integrates Digitraffic and FMI open data using **Feign clients**, and parses FMI’s XML responses using **JAXB**.

----------

## **Environment Requirements**

-   **Java** 17.0.16 (Eclipse Temurin recommended)
-   **Maven** 3.8+
-   **Spring Boot** 3.5.7
-   Internet access (required for external API calls)

----------

## **Getting Started**

### **Clone the Repository**

```bash
git clone https://github.com/theanasuddin/Smart-Road-Weather-Dashboard.git
cd Smart-Road-Weather-Dashboard
cd smart-road-weather-dashboard

```

----------

## **Build and Run**

### **Containerized Run with Docker (Recommended)**

**Build the Docker image:**

```bash
docker build -t smart-road-weather-backend .

```

**Run the container:**

```bash
docker run --name smart-road-weather-backend -p 8080:8080 smart-road-weather-backend

```

**Run with healthcheck monitoring (optional):**

```bash
docker run -d --name backend -p 8080:8080 \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" \
  smart-road-weather-backend

```

**Remove an existing Docker image (if needed):**

```bash
docker rmi smart-road-weather-backend

```

----------

### **Local / Native Run (Without Docker)**

**Build and launch with Maven:**

```bash
mvn clean install
mvn spring-boot:run

```

**Or build a JAR and run:**

```bash
mvn clean package
java -jar target/smart-road-weather-dashboard-0.0.1-SNAPSHOT.jar

```

----------

## **Swagger UI — API Documentation**

Swagger/OpenAPI UI is included for interactive API exploration.

After starting the application, open:

```
http://localhost:8080/swagger-ui.html

```

or

```
http://localhost:8080/swagger-ui/index.html

```

----------

## **Project Structure**

```
backend/
├── config/               # Swagger/OpenAPI and application configuration
├── controller/           # REST controllers exposing endpoints
├── service/              # Business logic and data processing
├── infrastructure/       # Feign clients for external APIs
├── model/                # DTOs and JAXB models
│   ├── digitraffic/
│   ├── fmi/
│   ├── request/
│   └── response/
└── util/                 # Utility classes (XML parsing, helpers)

```

----------

## **Dependencies**

Key Maven dependencies include:

-   **spring-boot-starter-web** — REST API framework
-   **spring-boot-starter-openfeign** — Declarative HTTP clients
-   **lombok** — Reduces boilerplate
-   **jakarta.xml.bind-api**, **jaxb-runtime** — XML parsing (JAXB)
-   **springdoc-openapi-starter-webmvc-ui** — Swagger/OpenAPI UI

All dependencies are automatically managed via Maven.

----------

## **Troubleshooting**

-   Ensure **Java 17** is installed and `JAVA_HOME` is correctly set
-   If Feign calls fail, verify:
    -   Internet connection
    -   Digitraffic/FMI API availability
-   JAXB errors typically indicate missing dependencies
-   Check logs for API integration issues
-   If Swagger UI doesn’t load, confirm the OpenAPI dependency is included
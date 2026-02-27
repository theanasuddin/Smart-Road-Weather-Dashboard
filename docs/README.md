# **Smart Road Weather Dashboard — Full System Setup Guide**

This guide explains how to run the **frontend** and **backend** services together using Docker, as well as how to run them individually if needed.

----------

## **Technology Stack**

### **Frontend**

-   React
-   Redux
-   Material UI
-   Recharts
-   Leaflet

### **Backend**

-   Java Spring Boot

### **Containerization**

-   Docker (frontend + backend containers)

### **APIs Used**

-   **Digitraffic** — road conditions, traffic flow, cameras, maintenance
-   **FMI** — weather forecasts, observations, warnings
-   **Open Matoe API** — prediction data for graphs

----------

## **Installation & Setup**

### **Recommended: Docker (Single Image)**

Run both frontend and backend together using Docker Compose.

----------

## **Prerequisites**

Ensure the following are installed:

-   **Java** 17.0.16 (Eclipse Temurin recommended)
-   **Node** 22.12.0 or higher
-   **npm** 10.9.0 or higher
-   **Docker** 28.5.1 or higher
-   **Maven** 3.8 or higher
-   **Spring Boot** 3.5.7
-   Internet access (required for Digitraffic & FMI API calls)

----------

## **Steps**

### **1. Clone the repository**

```bash
git clone https://github.com/theanasuddin/Smart-Road-Weather-Dashboard.git
cd Smart-Road-Weather-Dashboard

```

----------

### **2. Build & Run with Docker Compose**

```bash
docker compose up --build

```

Once the build completes, access the UI at:

**http://localhost:3000**

----------

### **3. Stop the services**

```bash
docker compose down

```

----------

## **Running Frontend & Backend Individually (Not Recommended)**

If you prefer to run services separately:

-   **[Backend setup guide](https://github.com/theanasuddin/Smart-Road-Weather-Dashboard/blob/master/smart-road-weather-dashboard/README.md)**  
-   **[Frontend setup guide](https://github.com/theanasuddin/Smart-Road-Weather-Dashboard/blob/master/UI/README.md)**  

# **Smart Road Weather Dashboard — Frontend**

The **Smart Road Weather Dashboard** is a modern web application built with **React, TypeScript, and Vite**. It provides real‑time traffic and weather analytics for regions across Finland, offering interactive maps, trend visualizations, and location‑based monitoring.

----------

## **Key Features**

-   **Real‑time Data Visualization** — Live traffic and weather monitoring
-   **Interactive Regional Maps** — Finland‑specific geospatial views
-   **Analytics & Trends** — Historical data and pattern insights
-   **Multi‑View Dashboard** — Switch between different perspectives
-   **Responsive UI** — Optimized for all screen sizes

----------

## **Environment Requirements**

-   **Node** 22.12.0 or higher
-   **npm** 10.9.0 or higher
-   **Docker** 28.5.1 or higher

----------

## **Getting Started**

### **Clone the Repository**

```bash
git clone https://github.com/theanasuddin/Smart-Road-Weather-Dashboard.git
cd Smart-Road-Weather-Dashboard
cd UI

```

----------

## **Build & Run (Local Development)**

1.  Navigate to the dashboard directory:
    
    ```bash
    cd dashboard
    
    ```
    
2.  Install dependencies:
    
    ```bash
    npm install
    
    ```
    
3.  Start the development server:
    
    ```bash
    npm run dev
    
    ```
    
4.  Access the frontend at:  
    **http://localhost:5173**

API requests will automatically proxy to the backend.

----------

## **Check Testing Score**

1.  Navigate to the dashboard directory:
    
    ```bash
    cd dashboard
    
    ```
    
2.  Run tests:
    
    ```bash
    npm test
    
    ```
    
3.  View the test summary in the terminal.

----------

## **Using Docker (Recommended)**

### **Build the Docker Image**

```bash
docker build -t dashboard .

```

### **Run the Container**

```bash
docker run -d -p 4000:80 --name dashboard dashboard:latest

```

Frontend will be available at:  
**http://localhost:4000**

----------

## **Project Structure**

```
src/
├── components/                     # Reusable UI components
│   ├── Sidebar.tsx                 # Navigation sidebar
│   └── Topbar.tsx                  # Top navigation bar
├── containers/                     # Layout containers
│   └── ViewContainer.tsx           # Main view wrapper
├── hooks/                          # Custom React hooks
│   ├── useAxios.tsx                # HTTP request hook
│   └── useWebsocket.tsx            # WebSocket hook
├── views/                          # Application pages
│   ├── AnalyticsandTrends.tsx      # Analytics dashboard
│   ├── FinlandRegionsMap.tsx       # Finland map visualization
│   ├── HomeView.tsx                # Home dashboard
│   ├── LocationView.tsx            # Location-specific data
│   └── TrafficWeatherDashboard.tsx # Combined traffic & weather view
├── App.tsx                         # Root component
├── main.tsx                        # Entry point
└── index.css                       # Global styles

public/
├── fnMap.png                       # Finland map asset
└── index.html                      # HTML template

config/
├── tsconfig.json                   # TypeScript config
├── tsconfig.app.json               # App-specific TS config
├── vite-env.d.ts                   # Vite environment types
├── eslint.config.js                # ESLint rules
└── package.json                    # Dependencies & scripts

docs/
└── README.md                       # Documentation

```

----------

## **Data Flow**

1.  **Data Acquisition** — External APIs via Axios + WebSocket streams
2.  **State Management** — React hooks for state and data processing
3.  **Visualization** — Interactive charts, maps, and UI components
4.  **User Interaction** — Responsive navigation and dynamic views

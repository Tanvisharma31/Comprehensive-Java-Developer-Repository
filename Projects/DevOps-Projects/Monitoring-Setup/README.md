# Monitoring Setup

Monitoring and logging setup for Java applications using Prometheus and Grafana.

## Features

- Application metrics collection
- Performance monitoring
- Error tracking
- Dashboard visualization
- Alert configuration

## Components

### Prometheus
- Metrics collection
- Time-series database
- Query language (PromQL)

### Grafana
- Visualization dashboards
- Alert management
- Data source integration

### Spring Boot Actuator
- Health checks
- Metrics endpoint
- Application info

## Setup

1. Add dependencies to `pom.xml`
2. Configure Prometheus endpoint
3. Set up Prometheus server
4. Configure Grafana dashboards
5. Set up alerts

## Configuration

### application.yml
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  metrics:
    export:
      prometheus:
        enabled: true
```

## Metrics Tracked

- HTTP request rate
- Response times
- Error rates
- JVM metrics
- Database connection pool
- Custom business metrics

## Dashboards

- Application overview
- Performance metrics
- Error tracking
- System resources

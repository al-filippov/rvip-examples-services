package ru.ulstu.is.demo.controller;

import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {
    private static final int MSECS = 1000;

    private final HealthIndicator healthIndicator;
    private final long timestamp;

    public HealthController(HealthAggregator healthAggregator,
                            Map<String, HealthIndicator> healthIndicators) {
        super();
        CompositeHealthIndicator healthIndicator = new CompositeHealthIndicator(
                healthAggregator);
        for (Map.Entry<String, HealthIndicator> entry : healthIndicators.entrySet()) {
            healthIndicator.addHealthIndicator(getKey(entry.getKey()), entry.getValue());
        }
        this.healthIndicator = healthIndicator;

        timestamp = System.currentTimeMillis();
    }

    private String getKey(String name) {
        int index = name.toLowerCase().indexOf("healthindicator");
        return (index > 0) ? name.substring(0, index) : name;
    }

    @GetMapping()
    public Map<String, Object> invoke() {
        Map<String, Object> result = new LinkedHashMap<>();
        final Runtime runtime = Runtime.getRuntime();
        result.put("uptime", (System.currentTimeMillis() - this.timestamp) / MSECS);
        result.put("systemload", ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage()
                / runtime.availableProcessors());
        result.put("memfree", runtime.freeMemory());
        result.put("memtotal", runtime.maxMemory());
        result.putAll(healthIndicator.health().getDetails());
        return result;
    }
}

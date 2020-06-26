package org.manu.gcp.metrics.stackdriver;

import io.micrometer.stackdriver.StackdriverConfig;
import io.micrometer.stackdriver.StackdriverMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConditionalOnExpression(
        value = "${management.metrics.export.stackdriver.enabled:true}"
)
@ConditionalOnProperty(value = "GCP_PROJECT_ID")
public class StackdriverMetricsConfig {

    @Bean
    StackdriverConfig stackdriverConfig(String projectId) {
        return new StackdriverConfig() {
            @Override
            public String projectId() {
                log.info("Project id is {}", projectId);
                return projectId;
            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public int batchSize() {
                int batchSize = 199;
                log.info("Batch size is {}", batchSize);
                return batchSize;
            }
        };
    }

    @Bean
    StackdriverMeterRegistry meterRegistry(@Value("${GCP_PROJECT_ID}") String projectId) {
        return StackdriverMeterRegistry.builder(stackdriverConfig(projectId)).build();
    }
}

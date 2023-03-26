package org.devshred;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleJobWorkers {
    @JobWorker(type = "logging")
    public void loggingJob(final ActivatedJob job) {
        log.info("type: {}, key: {}, elementId: {}, processInstanceKey: {}", job.getType(), job.getKey(), job.getElementId(), job.getProcessInstanceKey());
    }
}

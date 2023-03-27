package org.devshred;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleJobWorkers {
    @JobWorker(type = "logging")
    public void loggingJob(final ActivatedJob job, @Variable String someId) {
        log.info("someId: {}, type: {}, key: {}, elementId: {}, processInstanceKey: {}", //
                someId, job.getType(), job.getKey(), job.getElementId(), job.getProcessInstanceKey());
    }
}

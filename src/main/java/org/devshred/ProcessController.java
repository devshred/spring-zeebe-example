package org.devshred;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c")
@Slf4j
public class ProcessController {
    @Autowired
    private ZeebeClient client;

    @GetMapping
    public ResponseEntity<String> startProcess() {
        log.info("about to start");

        final ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId("simple-process")
                .latestVersion()
                .send()
                .join();

        log.info("started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'",
                event.getProcessDefinitionKey(), event.getBpmnProcessId(), event.getVersion(), event.getProcessInstanceKey());

        return ResponseEntity.ok("done");
    }
}

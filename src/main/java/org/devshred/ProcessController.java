package org.devshred;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/c")
@Slf4j
public class ProcessController {
    private static final Gson GSON = new Gson();

    @Autowired
    private ZeebeClient client;

    @GetMapping
    public ResponseEntity<String> startProcess(@RequestParam(required = false) String id) {
        log.info("about to start");

        var someId = new SomeId(Optional.ofNullable(id).orElse(UUID.randomUUID().toString()));
        final ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId("simple-process")
                .latestVersion()
                .variables(GSON.toJson(someId))
                .send()
                .join();

        log.info("started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'",
                event.getProcessDefinitionKey(), event.getBpmnProcessId(), event.getVersion(), event.getProcessInstanceKey());

        return ResponseEntity.ok("done");
    }
}

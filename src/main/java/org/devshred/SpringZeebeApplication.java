package org.devshred;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath:bpmn/simple-process.bpmn")
public class SpringZeebeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringZeebeApplication.class, args);
    }

}

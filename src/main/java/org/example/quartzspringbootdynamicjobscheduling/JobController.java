package org.example.quartzspringbootdynamicjobscheduling;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/jobs")
    void createJob(@RequestBody CreateJobRequestDTO createJobRequestDTO) {
        jobService.createJob(createJobRequestDTO.id(), createJobRequestDTO.frequencyInSeconds());
    }

    record CreateJobRequestDTO(String id, Integer frequencyInSeconds) implements Serializable {
    }
}

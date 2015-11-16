package com.infosys.zdportal.web.rest;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chackomathew on 16/11/15.
 */
@RestController
@RequestMapping("/api")
public class BatchResource {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String upload(@RequestParam("file") MultipartFile multipartFile)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException {

        try {
            byte[] bytes = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            System.out.println(fileName);
            File file = new File(fileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
            String absolutePath = file.getAbsolutePath();

            JobParameters jobParameters = new JobParametersBuilder().addString("filename", absolutePath).toJobParameters();
            JobExecution run = jobLauncher.run(job, jobParameters);

            ExitStatus exitStatus = run.getExitStatus();
            return exitStatus.getExitCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failure";

    }
}

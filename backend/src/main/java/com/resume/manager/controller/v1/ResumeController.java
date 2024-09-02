package com.resume.manager.controller.v1;

import com.resume.manager.exception.ResumeNotFoundException;
import com.resume.manager.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;


    @RequestMapping(method = RequestMethod.GET,path = "/{name}")
    public ResponseEntity<String> loadResume(@PathVariable String name) {
        try {
            String resume = resumeService.getResume(name);
            // Set HTTP Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);

            // Return the content as a ResponseEntity
            return new ResponseEntity<>(resume, headers, HttpStatus.OK);

        } catch (IOException e) {
            // Handle the exception if the file is not found or cannot be read
            return new ResponseEntity<>("Resume not found", HttpStatus.NOT_FOUND);
        } catch (ResumeNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{name}/{resourceName}")
    public ResponseEntity loadResources(@PathVariable String name, @PathVariable String resourceName) {
        try {
            byte[] data = resumeService.getResources(name,resourceName);
            // Set HTTP Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity(data, headers, HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity("Resource not found", HttpStatus.NOT_FOUND);
        }

    }
}

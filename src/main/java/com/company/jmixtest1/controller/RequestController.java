package com.company.jmixtest1.controller;

import com.company.jmixtest1.entity.Request;
import com.company.jmixtest1.pojo.RequestResult;
import com.company.jmixtest1.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.UUID;

@RestController
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping("/calculate")
    public RequestResult calculate(@RequestBody Request request) {
        return requestService.calculate(request);
    }

    @PostMapping("/save")
    public Request save(@RequestBody Request request) {
        return requestService.save(request);
    }

    @PostMapping("/export")
    public RequestResult export(@RequestBody Request request) {
        return requestService.export(request);
    }

    @PostMapping("/import")
    public Object import_(@RequestParam MultipartFile input) {
        RequestResult result = new RequestResult();
        try {
            return requestService.import_(input);
        } catch (IOException e) {
            result.setResultMessage("error occurred during I/O operation");
        } catch (JAXBException e) {
            result.setResultMessage("error in parsing XML");
        }
        return result;
    }

   @PostMapping("/exportById")
    public RequestResult exportById(@RequestParam String id) {
        Request request = requestService.loadById(UUID.fromString(id));
        return requestService.export(request);
    }
}
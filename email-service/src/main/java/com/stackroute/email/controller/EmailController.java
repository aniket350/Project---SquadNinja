package com.stackroute.email.controller;
import com.stackroute.email.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
//This logging API allows you to configure which message types are written.
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
//service method
    private NotificationService notificationService;

//Add the dependencies. Here we performed construction autowired.
    @Autowired
    public EmailController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

}

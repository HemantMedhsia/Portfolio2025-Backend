package com.hemant.contactus.controller;
import com.hemant.contactus.dto.ApiResponse;
import com.hemant.contactus.model.MailRequest;
import com.hemant.contactus.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/contact")
@CrossOrigin("*")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ApiResponse<MailRequest> sendMail(@RequestBody MailRequest mailRequest) {
            mailService.sendHtmlMail(mailRequest);
            return ApiResponse.<MailRequest>builder()
                    .status("Success")
                    .message("mail send successfully to: " + mailRequest.getTo())
                    .data(mailRequest)
                    .timestamp(LocalDateTime.now())
                    .build();

    }
}

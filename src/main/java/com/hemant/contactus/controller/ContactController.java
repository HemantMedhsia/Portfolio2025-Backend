package com.hemant.contactus.controller;

import com.hemant.contactus.dto.ApiResponse;
import com.hemant.contactus.model.ContactModel;
import com.hemant.contactus.model.MailRequest;
import com.hemant.contactus.service.ContactService;
import com.hemant.contactus.service.MailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/contact")
@CrossOrigin("*")
public class ContactController {

    @Autowired
    ContactService contactService;
    @Autowired
    private MailService mailService;

    @PostMapping("/save-contact")
    public ApiResponse<ContactModel> saveContacts(@Valid @RequestBody ContactModel contactModel) {
        ContactModel saved = contactService.saveMsg(contactModel);
        MailRequest mailRequest = new MailRequest();
        mailRequest.setBody("I'm truly grateful that you reached out to me.\n" +
                "\n" +
                "Your message has been received, and I’ll get back to you as soon as possible.");
        mailRequest.setSubject("Thanks for reaching out");
        mailRequest.setTo(contactModel.getEmail());
        mailRequest.setName(contactModel.getName());
        mailService.sendHtmlMail(mailRequest);

        return ApiResponse.<ContactModel>builder()
                .status("success")
                .message("Contact saved successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

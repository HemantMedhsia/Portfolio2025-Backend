package com.hemant.contactus.controller;

import com.hemant.contactus.dto.ApiResponse;
import com.hemant.contactus.model.ContactModel;
import com.hemant.contactus.service.ContactService;
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

    @PostMapping("/save-contact")
    public ApiResponse<ContactModel> saveContacts(@Valid @RequestBody ContactModel contactModel) {
        ContactModel saved = contactService.saveMsg(contactModel);
        return ApiResponse.<ContactModel>builder()
                .status("success")
                .message("Contact saved successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

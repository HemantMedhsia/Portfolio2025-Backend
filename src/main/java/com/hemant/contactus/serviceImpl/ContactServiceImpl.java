package com.hemant.contactus.serviceImpl;

import com.hemant.contactus.model.ContactModel;
import com.hemant.contactus.repository.ContactRepository;
import com.hemant.contactus.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public ContactModel saveMsg(ContactModel contact) {
        return contactRepository.save(contact);
    }
}

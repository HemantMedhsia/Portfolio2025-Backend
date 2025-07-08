package com.hemant.contactus.repository;

import com.hemant.contactus.model.ContactModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<ContactModel, String> {
}

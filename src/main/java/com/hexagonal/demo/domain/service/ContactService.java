package com.hexagonal.demo.domain.service;

import com.hexagonal.demo.domain.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();

    Contact getContactById(Long contactId);

    Contact createContact(Contact contact);

    Contact updateContact(Long contactId, Contact contact);

    void deleteContact(Long contactId);
}

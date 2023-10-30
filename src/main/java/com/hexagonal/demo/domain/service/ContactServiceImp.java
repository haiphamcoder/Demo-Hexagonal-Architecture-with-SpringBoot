package com.hexagonal.demo.domain.service;

import com.hexagonal.demo.domain.entity.Contact;
import com.hexagonal.demo.ports.input.ContactService;
import com.hexagonal.demo.adapters.persistence.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImp implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImp(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long contactid, Contact contact) {
        Contact existingContact = contactRepository.findById(contactid).orElse(null);
        if(existingContact != null) {
            existingContact.setName(contact.getName());
            existingContact.setEmail(contact.getEmail());
            existingContact.setPhone(contact.getPhone());
            return contactRepository.save(existingContact);
        }
        return null;
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }
}

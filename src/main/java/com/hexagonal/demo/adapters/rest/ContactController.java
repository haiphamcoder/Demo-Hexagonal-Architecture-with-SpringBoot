package com.hexagonal.demo.adapters.rest;

import com.hexagonal.demo.domain.entity.Contact;
import com.hexagonal.demo.ports.input.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts/")
    public ResponseEntity<List<Contact>> listAllContacts() {
        List<Contact> listContacts = contactService.getAllContacts();
        if (listContacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listContacts, HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public Contact getContactById(@PathVariable("id") Long contactId) {
        Contact contact = contactService.getContactById(contactId);
        if (contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    @PostMapping("/contacts/")
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long contactId, @RequestBody Contact contact) {
        Contact existingContact = contactService.getContactById(contactId);
        if (existingContact == null) {
            return ResponseEntity.notFound().build();
        }
        existingContact.setName(contact.getName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setPhone(contact.getPhone());
        Contact updatedContact = contactService.updateContact(contactId, existingContact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable("id") Long contactId) {
        Contact contact = contactService.getContactById(contactId);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        contactService.deleteContact(contactId);
        return ResponseEntity.ok().build();
    }
}

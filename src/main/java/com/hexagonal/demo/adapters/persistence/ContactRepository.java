package com.hexagonal.demo.adapters.persistence;

import com.hexagonal.demo.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
package com.hexagonal.demo.ports.output;

import com.hexagonal.demo.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}

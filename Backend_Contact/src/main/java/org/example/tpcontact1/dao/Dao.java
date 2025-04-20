package org.example.tpcontact1.dao;

import org.example.tpcontact1.bean.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Dao extends JpaRepository<Contact, Long> {
    Contact findByName(String name);
}

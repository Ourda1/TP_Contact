package org.example.tpcontact1.ws;

import org.example.tpcontact1.dao.Dao;
import org.example.tpcontact1.bean.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private Dao contactDao;

    // 🔹 GET all contacts
    @GetMapping("/")
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    // 🔹 GET contact by name
    @GetMapping("/name/{name}")
    public Contact findByName(@PathVariable String name) {
        return contactDao.findByName(name);
    }

    // 🔹 POST new contact
    @PostMapping("/")
    public Contact save(@RequestBody Contact contact) {
        return contactDao.save(contact);
    }

    // 🔹 DELETE contact by id
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        contactDao.deleteById(id);
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactDao.findAll();
    }
}

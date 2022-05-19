package com.example.real_soft_task.repository;

import com.example.real_soft_task.dto.ContactDto;
import com.example.real_soft_task.model.Contact;

import java.util.List;

public interface ContactRepository {

    int addContact(ContactDto contact);

    int updateContact(ContactDto contact, Integer id);

    Contact findById(Integer id);

    List<Contact> findAll();

    int deleteContact(Integer id);
}

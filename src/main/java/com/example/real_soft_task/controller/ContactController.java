package com.example.real_soft_task.controller;

import com.example.real_soft_task.dto.ContactDto;
import com.example.real_soft_task.model.Category;
import com.example.real_soft_task.model.Contact;
import com.example.real_soft_task.repository_service.CategoryService;
import com.example.real_soft_task.repository_service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
   private final CategoryService categoryService;
    @GetMapping("/crud")
    public String allContact(Model model) {
        List<Contact> all = contactService.findAll();
        model.addAttribute("message", "All Contact");
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("contactList", all);
        return "contact";
    }

    @GetMapping("/{id}")
    public String getOneCat(@PathVariable Integer id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("contactList", contactService.findAll());
        model.addAttribute("contact", contact);
        return "contact";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("contactList", contactService.findAll());
        model.addAttribute("categoryList",categoryService.findAll());
        return "contact";
    }

    @PostMapping("/add")
    public String addContact(@ModelAttribute ContactDto dto, Model model) {
        contactService.addContact(dto);
        List<Contact> all = contactService.findAll();
        model.addAttribute("message", "Added");
        model.addAttribute("contactList", all);
        model.addAttribute("categoryList",categoryService.findAll());
        return "contact";
    }

    @PostMapping("/addcon")
    public String addCo(@ModelAttribute ContactDto dto, Model model) {
        contactService.addContact(dto);
        List<Contact> all = contactService.findAll();
        model.addAttribute("message", "Added");
        model.addAttribute("contactList", all);
        model.addAttribute("categoryList",categoryService.findAll());
        return "userPage";
    }
    @GetMapping("/edit/{id}")
    public String editContact(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", contactService.findById(id));
        model.addAttribute("contactList",contactService.findAll());
        model.addAttribute("categoryList",categoryService.findAll());
        return "contact_edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditContact(@ModelAttribute ContactDto dto, @PathVariable Integer id, Model model) {
        contactService.updateContact(dto,id);
        model.addAttribute("message", "Edited");
        model.addAttribute("contactList", contactService.findAll());
        model.addAttribute("categoryList",categoryService.findAll());
        return "contact";
    }
    @PostMapping("/edited/{id}")
    public String saveEdit(@ModelAttribute ContactDto dto, @PathVariable Integer id, Model model) {
        contactService.updateContact(dto,id);
        model.addAttribute("message", "Edited");
        model.addAttribute("contactList", contactService.findAll());
        model.addAttribute("categoryList",categoryService.findAll());
        return "userPage";
    }
    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Integer id,Model model){
        contactService.deleteContact(id);
        model.addAttribute("message","Deleted");
        model.addAttribute("contactList",contactService.findAll());
        model.addAttribute("categoryList",categoryService.findAll());
        return "contact";
    }
    @GetMapping("/show")
    public String contactList(Model model){
        List<Contact> all = contactService.findAll();
        model.addAttribute("message","All Contact");
        model.addAttribute("contactList",all);
        return "contact-show";
    }

    @GetMapping("/addContact")
    public String addcon(Model model){
        List<Contact> all = contactService.findAll();
        model.addAttribute("contactList",all);
        model.addAttribute("categoryList",categoryService.findAll());
        return "yourselfAdd";
    }


}

package ua.skillsup.socialnetwork.community.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import socialnetwork.community.api.JavaContactService;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.api.model.PlaceDto;
import socialnetwork.community.dao.*;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.RoleType;

import java.util.Collections;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@Controller
public class AdminController {

    @Autowired
    private JavaContactService javaContactService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @RequestMapping(value = "/registrationContact", method = RequestMethod.POST)
    public ContactDto register(@RequestBody ContactDto contactDto) {
        if ((contactDto.getFirstName().trim().isEmpty()) || (contactDto.getLastName().trim().isEmpty())
                || (contactDto.getUsername().trim().isEmpty()) || (contactDto.getPassword().trim().isEmpty()))
            return contactDto;
        if (javaContactService.isUsernameExists(contactDto.getUsername())) {
            contactDto.setId(new Long(0));
            return contactDto;
        }
        Contact contact = convert(contactDto);
        contact.setPassword(passwordEncoder.encode(contactDto.getPassword()));
        contact.addRole(roleService.findByType(RoleType.USER));
        javaContactService.save(convert(contact));
        return javaContactService.findContactByUsername(contactDto.getUsername());

    }

    @ResponseBody
    @RequestMapping(value = "/allContacts", method = RequestMethod.GET)
    public List<ContactDto> getAllContacts() {
        List<ContactDto> result = javaContactService.getAllContacts();
        Collections.sort(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/allHobbies", method = RequestMethod.GET)
    public List<HobbyDto> getAllHobbies() {
        List<HobbyDto> result = javaContactService.getAllHobbies();
        Collections.sort(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/allPlaces", method = RequestMethod.GET)
    public List<PlaceDto> getAllPlaces() {
        List<PlaceDto> result = javaContactService.getAllPlaces();
        Collections.sort(result);
        return result;
    }

}

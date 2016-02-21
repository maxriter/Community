package ua.skillsup.socialnetwork.community.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import socialnetwork.community.api.JavaContactService;
import socialnetwork.community.api.model.*;
import socialnetwork.community.dao.*;
import socialnetwork.community.dao.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;


@Controller
public class MainController {

    @Autowired
    private JavaContactService javaContactService;

    @Autowired
    ContactDao contactDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactPage(ModelMap model) {
        ContactDto contactDto = javaContactService.findContactByUsername(getPrincipal());
        model.addAttribute("contact", contactDto);
        model.addAttribute("hobbies", javaContactService.getAllContactHobbies(getPrincipal()));
        model.addAttribute("places", javaContactService.getAllContactPlaces(getPrincipal()));
        return "contact";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("contact", getPrincipal());
        return "admin";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("contact", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }


    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "newuser";
    }

    private String getPrincipal() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @ResponseBody
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public ContactDto editProfile(@RequestBody ContactDto contact) {
        if ((contact.getFirstName().trim().isEmpty()) && (contact.getLastName().trim().isEmpty()) && (contact.getBirthDate() == null) && (contact.getPhone().trim().isEmpty())) {
            return new ContactDto();
        }
        return javaContactService.editContactProfile(contact, getPrincipal());
    }

    @ResponseBody
    @RequestMapping(value = "/addHobby", method = RequestMethod.POST)
    public HobbyDto addHobby(@RequestBody HobbyDto hobbyDto) {
        if ((hobbyDto.getTitle().trim().isEmpty()) || (javaContactService.hasHobby(hobbyDto.getTitle(), getPrincipal()) == true)) {
            return hobbyDto;
        }
        long id = javaContactService.addHobby(hobbyDto);
        HobbyDto hobby = javaContactService.findHobbyById(id);
        javaContactService.addHobbyToContact(hobby, getPrincipal());
        return hobby;
    }

    @ResponseBody
    @RequestMapping(value = "/addPlace", method = RequestMethod.POST)
    public PlaceDto addPlace(@RequestBody PlaceDto placeDto) {
        if ((placeDto.getTitle().trim().isEmpty()) || (javaContactService.hasPlace(placeDto.getTitle(), getPrincipal()) == true)) {
            return placeDto;
        }
        long id = javaContactService.addPlace(placeDto);
        PlaceDto place = javaContactService.findPlaceById(id);
        javaContactService.addPlaceToContact(place, getPrincipal());
        return place;
    }

    @ResponseBody
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public PostDto addPost(@RequestBody PostDto postDto) {
        if (postDto.getContent().trim().isEmpty()) {
            return postDto;
        }
        return javaContactService.addPostToContact(postDto, getPrincipal());
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
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
        contactDao.save(contact);
        return javaContactService.findContactByUsername(contactDto.getUsername());
    }

    @ResponseBody
    @RequestMapping(value = "/searchContacts", method = RequestMethod.POST)
    public List<ContactDto> searchContacts(@RequestBody String param) {
        return javaContactService.findContactsByParam(param, getPrincipal());
    }

    @ResponseBody
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public ContactDto addFriend(@RequestBody Long id) {
        ContactDto friend = javaContactService.findContactById(id);
        ContactDto contact = javaContactService.findContactByUsername(getPrincipal());
        return javaContactService.addFriendship(friend, contact);
    }

    @ResponseBody
    @RequestMapping(value = "/removeFriend", method = RequestMethod.POST)
    public ContactDto removeFriend(@RequestBody Long id) {
        ContactDto friend = javaContactService.findContactById(id);
        ContactDto contact = javaContactService.findContactByUsername(getPrincipal());
        javaContactService.removeFriendship(friend, contact);
        return friend;
    }

    @ResponseBody
    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public MessageDto sendMessage(@RequestBody MessageDto messageDto) {
        if (messageDto.getContent().trim().isEmpty()) return messageDto;
        messageDto.setContact_to(messageDto.getId());
        messageDto.setContact_from(javaContactService.findContactByUsername(getPrincipal()).getId());
        messageDto.setMessage_date(LocalDateTime.now());
        messageDto.setId(null);
        Long id = javaContactService.storeMessage(messageDto);
        MessageDto message = javaContactService.findMessageById(id);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/contactById", method = RequestMethod.POST)
    public ContactDto searchContactById(@RequestBody ContactDto contactDto) {
        ContactDto contact = javaContactService.findContactById(contactDto.getId());
        return contact;
    }

    @ResponseBody
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public List<ContactDto> getFriends() {
        return javaContactService.getAllFriendsByContact(getPrincipal());
    }

    @ResponseBody
    @RequestMapping(value = "/sendMessages", method = RequestMethod.GET)
    public List<MessageDto> getSendMessages() {
        ContactDto contact = javaContactService.findContactByUsername(getPrincipal());
        List<MessageDto> result = javaContactService.getSendMessages(contact);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/receivedMessages", method = RequestMethod.GET)
    public List<MessageDto> getReceivedMessages() {
        ContactDto contact = javaContactService.findContactByUsername(getPrincipal());
        List<MessageDto> result = javaContactService.getReceivedMessages(contact);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<PostDto> getPostsByContact() {
        return javaContactService.getPostsByContact(javaContactService.findContactByUsername(getPrincipal()).getId());
    }

    @ResponseBody
    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
    public List<PostDto> getFriendsPost() {
        return javaContactService.getFriendsPosts(getPrincipal());
    }

    @ModelAttribute("roles")
    public List<Role> initializeProfiles() {
        return roleService.findAll();
    }

}
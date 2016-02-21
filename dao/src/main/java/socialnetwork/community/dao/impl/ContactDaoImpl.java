package socialnetwork.community.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.api.model.PostDto;
import socialnetwork.community.dao.AbstractDao;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.HobbyDao;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.Post;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@Repository
@Transactional(readOnly = true)
public class ContactDaoImpl extends AbstractDao<Integer, Contact> implements ContactDao, Serializable {

    @Autowired
    private HobbyDao hobbyDao;

    @Autowired
    private SessionFactory sessionFactory;

    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = sessionFactory.getCurrentSession().createQuery("from Contact").list();
        List<ContactDto> result = new ArrayList<ContactDto>(contacts.size());
        for (Contact contact : contacts) {
            result.add(convert(contact));
        }
        return result;
    }

    @Transactional(readOnly = false)
    public long addContact(ContactDto contactDto) {
        Contact contact = convert(contactDto);
        sessionFactory.getCurrentSession().save(contact);
        sessionFactory.getCurrentSession().flush();

        return contact.getId();
    }

    @Transactional(readOnly = false)
    public ContactDto addFriendship(ContactDto friend, ContactDto principal) {
        List<ContactDto> friendsA = getFriendship(friend);
        List<Long> ids = new ArrayList<>();
        for (ContactDto contactDto : friendsA) {
            ids.add(contactDto.getId());
        }
        if (ids.contains(principal.getId())) return new ContactDto();
        Contact contactA = findByUsername(friend.getUsername());
        Contact contactB = findByUsername(principal.getUsername());
        contactA.addFriend(contactB);
        contactB.addFriend(contactA);
        sessionFactory.getCurrentSession().flush();
        return friend;
    }

    public List<ContactDto> getFriendship(ContactDto a) {
        List<Contact> contacts = sessionFactory.getCurrentSession().createQuery("select c from Contact c where c.username = :username").setParameter("username", a.getUsername()).list();
        Contact contact = contacts.iterator().next();
        Set<Contact> friends = contact.getFriendship();
        List<ContactDto> result = new ArrayList<>();
        for (Contact friend : friends) {
            result.add(convert(friend));
        }
        return result;
    }

    @Transactional(readOnly = false)
    public ContactDto removeFriendship(ContactDto a, ContactDto b) {
        Contact contactA = findByUsername(a.getUsername());
        Contact contactB = findByUsername(b.getUsername());
        contactA.removeFriend(contactB);
        contactB.removeFriend(contactA);
        sessionFactory.getCurrentSession().flush();
        return a;
    }

    @Transactional(readOnly = false)
    public void save(Contact contact) {
        persist(contact);
        sessionFactory.getCurrentSession().flush();
    }

    public Contact findById(int id) {
        return getByKey(id);
    }

    public ContactDto findById(Long id) {
        List<Contact> contacts = sessionFactory.getCurrentSession().createQuery("select c from Contact c where c.id = :id").setParameter("id", id).list();
        if (contacts.isEmpty()) {
            return null;
        } else {
            return convert(contacts.get(0));
        }
    }

    public Contact findByUsername(String username) {
        List<Contact> contacts = sessionFactory.getCurrentSession().createQuery("select c from Contact c where c.username = :username").setParameter("username", username).list();
        if (contacts.isEmpty()) {
            return null;
        } else {
            return contacts.get(0);
        }
    }

    @Transactional(readOnly = false)
    public ContactDto editContactProfile(ContactDto contactDto, String username) {
        Contact contact = findByUsername(username);
        String firstname = contactDto.getFirstName();
        String lastname = contactDto.getLastName();
        LocalDate birthDate = contactDto.getBirthDate();
        String phone = contactDto.getPhone();
        if (firstname != "") {
            contact.setFirstName(firstname);
        }
        if (lastname != "") {
            contact.setLastName(lastname);
        }
        if (birthDate != null) {
            contact.setBirthDate(birthDate);
        }
        if (phone != "") {
            contact.setPhone(phone);
        }
        sessionFactory.getCurrentSession().flush();
        return convert(contact);
    }

    @Transactional(readOnly = false)
    public boolean addHobbyToContact(HobbyDto hobbyDto, String username) {
        Long id = hobbyDao.addHobby(hobbyDto);
        findByUsername(username).addHobby(hobbyDao.findById(id));
        sessionFactory.getCurrentSession().flush();
        return true;
    }

    @Transactional(readOnly = false)
    public PostDto addPostToContact(PostDto postDto, String username) {
        Contact contact = findByUsername(username);
        Post post = new Post();
        post.setContact(contact);
        post.setContent(postDto.getContent());
        post.setPost_date(LocalDateTime.now());
        contact.addPost(post);
        sessionFactory.getCurrentSession().flush();
        return convert(post);
    }

    public List<ContactDto> getAllFriendsByContact(String username) {
        Contact contact = findByUsername(username);
        Set<Contact> friends = contact.getFriendship();
        List<ContactDto> result = new ArrayList<>();
        for (Contact friend : friends) {
            result.add(convert(friend));
        }
        return result;
    }

    public List<ContactDto> findByParam(String param, String username) {
        Contact user = findByUsername(username);
        List<ContactDto> friends = getAllFriendsByContact(username);
        List<Long> ids = new ArrayList<>();
        for (ContactDto contactDto : friends) {
            ids.add(contactDto.getId());
        }
        List<ContactDto> result = new ArrayList<>();
        List<ContactDto> contacts = getAllContacts();
        if (contacts.isEmpty()) {
            return null;
        }
        for (ContactDto contact : contacts) {
            if (ids.contains(contact.getId()) || contact.getId().equals(user.getId())) continue;
            if (contact.getFirstName().toLowerCase().contains(param.toLowerCase()) || contact.getLastName().toLowerCase().contains(param.toLowerCase())) {
                result.add(contact);
            }
        }
        return result;

    }

    public boolean isUsernameExists(String username) {
        ContactDto contact = convert(findByUsername(username));
        return (contact != null);
    }

}
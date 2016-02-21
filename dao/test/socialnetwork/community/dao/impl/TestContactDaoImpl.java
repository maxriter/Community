package socialnetwork.community.dao.impl;


import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.HobbyDao;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@ContextConfiguration({"classpath*:daoApplicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestContactDaoImpl {

    @Autowired
    ContactDao contactDao;

    @Autowired
    HobbyDao hobbyDao;

    @Autowired
    HobbyDao postDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    @Transactional
    public void testContactDatabaseEmpty() {
        Assert.assertEquals(contactDao.getAllContacts().size(), 2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddContact() {
        Long id = contactDao.addContact(convert(getSampleContact()));
        Assert.assertEquals(contactDao.getAllContacts().size(), 3);
        ContactDto contact = contactDao.findById(id);
        ContactDto sampleContact = convert(getSampleContact());
        Assert.assertEquals(contact.getFirstName(), sampleContact.getFirstName());
        Assert.assertEquals(contact.getLastName(), sampleContact.getLastName());
        Assert.assertEquals(contact.getPassword(), sampleContact.getPassword());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllContacts() {
        contactDao.save(getSampleContact());
        Assert.assertEquals(contactDao.getAllContacts().size(), 3);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddGetRemoveFriendship() {
        ContactDto first = contactDao.getAllContacts().get(0);
        ContactDto second = contactDao.getAllContacts().get(1);
        contactDao.addFriendship(first, second);
        ContactDto friendOfFirst = contactDao.getFriendship(first).get(0);
        ContactDto friendOfSecond = contactDao.getFriendship(second).get(0);
        Assert.assertEquals(second.getFirstName(), friendOfFirst.getFirstName());
        Assert.assertEquals(second.getLastName(), friendOfFirst.getLastName());
        Assert.assertEquals(second.getBirthDate(), friendOfFirst.getBirthDate());

        Assert.assertEquals(first.getFirstName(), friendOfSecond.getFirstName());
        Assert.assertEquals(first.getLastName(), friendOfSecond.getLastName());
        Assert.assertEquals(first.getBirthDate(), friendOfSecond.getBirthDate());

        contactDao.removeFriendship(first, second);
        Assert.assertEquals(0, contactDao.getFriendship(first).size());
        Assert.assertEquals(0, contactDao.getFriendship(second).size());
    }

    @Test
    @Transactional
    public void testFindById() {
        ContactDto contact = contactDao.findById(1L);
        Assert.assertEquals(contact.getFirstName(), "User1 First Name");
        Assert.assertEquals(contact.getLastName(), "User1 Last Name");
        Assert.assertEquals(contact.getUsername(), "user1");
        Assert.assertEquals(contact.getPhone(), "123456789");
    }

    @Test
    @Transactional
    public void testFindByUsername() {
        Contact contact = contactDao.findByUsername("user1");
        Assert.assertEquals(contact.getFirstName(), "User1 First Name");
        Assert.assertEquals(contact.getLastName(), "User1 Last Name");
        Assert.assertEquals(contact.getPhone(), "123456789");
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testEditContactProfile() {
        ContactDto editContact = new ContactDto();
        editContact.setFirstName("a");
        editContact.setLastName("b");
        editContact.setPhone("0");
        LocalDate birth = LocalDate.of(1900, 01, 01);
        editContact.setBirthDate(birth);
        contactDao.editContactProfile(editContact, "user1");
        Contact contact = contactDao.findByUsername("user1");
        Assert.assertEquals(contact.getFirstName(), "a");
        Assert.assertEquals(contact.getLastName(), "b");
        Assert.assertEquals(contact.getPhone(), "0");
        Assert.assertEquals(contact.getBirthDate(), birth);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddHobbyToContact() {
        HobbyDto hobby = hobbyDao.getAllHobbies().get(0);
        contactDao.addHobbyToContact(hobby, "user1");
        List<HobbyDto> hobbies = hobbyDao.getAllContactHobbies("user1");
        Assert.assertEquals(1, hobbies.size());
        HobbyDto hobbyDto = hobbies.get(0);
        Assert.assertEquals("jogging", hobbyDto.getTitle());
        Assert.assertEquals("just do it", hobbyDto.getDescription());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddPostToContact() {
        LocalDateTime postDateTime = LocalDateTime.of(1900, 01, 01, 01, 01);
        Contact contact = contactDao.findByUsername("user1");
        Post post = new Post();
        post.setContact(contact);
        post.setContent("Hi");
        post.setPost_date(postDateTime);
        contact.addPost(post);
        Assert.assertEquals("Hi", contact.getPosts().iterator().next().getContent());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGelAllFriendsByContact() {
        ContactDto first = contactDao.getAllContacts().get(0);
        ContactDto second = contactDao.getAllContacts().get(1);
        contactDao.addFriendship(first, second);
        List<ContactDto> friendList = contactDao.getAllFriendsByContact("user1");
        Assert.assertEquals(1, friendList.size());
        Assert.assertEquals("user2", friendList.get(0).getUsername());
    }

    @Test
    @Transactional
    public void testFindByParam() {
        List<ContactDto> searchResult = contactDao.findByParam("User2", "user1");
        Assert.assertEquals(1, searchResult.size());
        ContactDto contactDto = searchResult.get(0);
        Assert.assertEquals("User2 First Name", contactDto.getFirstName());

        ContactDto first = contactDao.getAllContacts().get(0);
        ContactDto second = contactDao.getAllContacts().get(1);
        contactDao.addFriendship(first, second);

        searchResult.clear();
        searchResult = contactDao.findByParam("User2", "user1");
        Assert.assertEquals(0, searchResult.size());
    }

    @Test
    @Transactional
    public void testIsUsernameExists() {
        boolean isExist;
        boolean isExistAnyUsername;
        isExist = contactDao.isUsernameExists("user1");
        isExistAnyUsername = contactDao.isUsernameExists("test");
        Assert.assertTrue(isExist);
        Assert.assertFalse(isExistAnyUsername);
    }

    public Contact getSampleContact() {
        Contact contact = new Contact();
        contact.setFirstName("First Name");
        contact.setLastName("Last Name");
        contact.setUsername("username");
        contact.setPassword("password");
        return contact;
    }


}

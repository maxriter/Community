package socialnetwork.community.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.MessageDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.MessageDao;

import java.time.LocalDateTime;
import java.util.List;

@ContextConfiguration({"classpath*:daoApplicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageDaoImpl {

    @Autowired
    MessageDao messageDao;

    @Autowired
    ContactDao contactDao;

    @Test
    @Transactional
    @Rollback(true)
    public void storeAndFindByIdMessage() {
        MessageDto message = new MessageDto();
        message.setContact_from(1L);
        message.setContact_to(2L);
        message.setMessage_date(LocalDateTime.now());
        message.setContent("Hi!");
        long id = messageDao.storeMessage(message);
        Assert.assertEquals("Hi!",messageDao.findById(id).getContent());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getConversation() {
        MessageDto message = new MessageDto();
        message.setContact_from(1L);
        message.setContact_to(2L);
        message.setMessage_date(LocalDateTime.now());
        message.setContent("Hi!");
        messageDao.storeMessage(message);
        ContactDto contactFrom=contactDao.getAllContacts().get(0);
        ContactDto contactTo=contactDao.getAllContacts().get(1);
        List<MessageDto> conversation = messageDao.getConversation(contactTo, contactFrom);
        Assert.assertEquals(1,conversation.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetSendAndReceivedMessages() {
        MessageDto message = new MessageDto();
        message.setContact_from(1L);
        message.setContact_to(2L);
        message.setMessage_date(LocalDateTime.now());
        message.setContent("Hi!");
        messageDao.storeMessage(message);
        ContactDto contactFrom=contactDao.getAllContacts().get(0);
        ContactDto contactTo=contactDao.getAllContacts().get(1);
        Assert.assertEquals(1,messageDao.getSend(contactFrom).size());
        Assert.assertEquals(0,messageDao.getReceived(contactFrom).size());
        Assert.assertEquals(1,messageDao.getReceived(contactTo).size());
        Assert.assertEquals(0,messageDao.getSend(contactTo).size());
    }

}

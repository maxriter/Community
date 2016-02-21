package socialnetwork.community.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.PostDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.PostDao;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@ContextConfiguration({"classpath*:daoApplicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPostDaoImpl {

    @Autowired
    PostDao postDao;

    @Autowired
    ContactDao contactDao;

    @Test
    @Transactional
    @Rollback(true)
    public void getAllPosts() {
        LocalDateTime postDateTime = LocalDateTime.of(1900, 01, 01, 01, 01);
        Contact contact = contactDao.findByUsername("user1");
        Post post = new Post();
        post.setContact(contact);
        post.setContent("Hi");
        post.setPost_date(postDateTime);
        Assert.assertEquals(0,  postDao.getAllPosts().size());
        contact.addPost(post);
        Assert.assertEquals(1, postDao.getAllPosts().size());
    }

    @Test
    @Transactional
    public void getFriendsPosts() {
        LocalDateTime postDateTime = LocalDateTime.of(1900, 01, 01, 01, 01);
        Contact user1 = contactDao.findByUsername("user1");
        Contact user2 = contactDao.findByUsername("user2");
        contactDao.addFriendship(convert(user1), convert(user2));
        Post post1 = new Post();
        post1.setContact(user1);
        post1.setContent("Hi! It's contact1");
        post1.setPost_date(postDateTime);
        Post post2 = new Post();
        post2.setContact(user2);
        post2.setContent("Hi! It's contact2");
        post2.setPost_date(postDateTime);
        user1.addPost(post1);
        user2.addPost(post2);
        List<PostDto> postsContact1 = postDao.getFriendsPosts("user2");
        List<PostDto> postsContact2 = postDao.getFriendsPosts("user1");
        Assert.assertEquals("Hi! It's contact1", postsContact1.get(0).getContent());
        Assert.assertEquals("Hi! It's contact2", postsContact2.get(0).getContent());

    }

    @Test
    @Transactional
    public void getPostsByContact() {
        LocalDateTime postDateTime = LocalDateTime.of(1900, 01, 01, 01, 01);
        Contact user1 = contactDao.findByUsername("user1");
        Post post1 = new Post();
        post1.setContact(user1);
        post1.setContent("Hi! 1");
        post1.setPost_date(postDateTime);
        user1.addPost(post1);
        List<PostDto> posts = postDao.getPostsByContact(contactDao.findByUsername("user1").getId());
        Assert.assertEquals("Hi! 1", posts.get(0).getContent());
        Post post2 = new Post();
        post2.setContact(user1);
        post2.setContent("Hi! 2");
        post2.setPost_date(postDateTime);
        user1.addPost(post2);
        Assert.assertEquals(2,postDao.getPostsByContact(contactDao.findByUsername("user1").getId()).size());
    }

}

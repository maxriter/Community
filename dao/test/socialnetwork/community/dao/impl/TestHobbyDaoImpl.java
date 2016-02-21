package socialnetwork.community.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.dao.HobbyDao;
import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.dao.entity.Hobby;

import java.util.List;


@ContextConfiguration({"classpath*:daoApplicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHobbyDaoImpl {

    @Autowired
    private HobbyDao hobbyDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testAddHobby() {
        HobbyDto hobby = new HobbyDto();
        hobby.setTitle("test");
        hobby.setDescription("test");
        hobbyDao.addHobby(hobby);
        Assert.assertEquals(2, hobbyDao.getAllHobbies().size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getAllHobbies() {
           List<HobbyDto> hobbies = hobbyDao.getAllHobbies();
        Assert.assertEquals(1,hobbies.size());
        HobbyDto hobby = hobbies.get(0);
        Assert.assertEquals("jogging",hobby.getTitle());
        Assert.assertEquals("just do it", hobby.getDescription());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void findByTitle() {
        List<Hobby> hobbies = hobbyDao.findByTitle("jogging");
        Assert.assertEquals(1,hobbies.size());
        Assert.assertEquals("just do it", hobbies.get(0).getDescription());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void findById() {
        Hobby hobby = hobbyDao.findById(1L);
        Assert.assertEquals("jogging", hobby.getTitle());
        Assert.assertEquals("just do it", hobby.getDescription());
    }

}

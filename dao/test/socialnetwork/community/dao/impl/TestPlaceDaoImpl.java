package socialnetwork.community.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.PlaceDto;
import socialnetwork.community.dao.PlaceDao;
import socialnetwork.community.dao.entity.Place;

import java.util.List;

@ContextConfiguration({"classpath*:daoApplicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPlaceDaoImpl {

    @Autowired
    PlaceDao placeDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testAddPlace() {
        PlaceDto place = new PlaceDto();
        place.setTitle("test");
        place.setDescription("test");
        place.setLatitude(0);
        place.setLongitude(0);
        Long id = placeDao.addPlace(place);
        Assert.assertEquals("test", placeDao.findById(id).getTitle());
    }

    @Test
    @Transactional
    public void testGetAllPlaces() {
        Assert.assertEquals(1,placeDao.getAllPlaces().size());
        Assert.assertEquals("Zurich", placeDao.getAllPlaces().get(0).getTitle());
    }

    @Test
    @Transactional
    public void testFindByTitle() {
        Place place= placeDao.findByTitle("Zurich");
        Assert.assertEquals("Europe's largest clock face is located in Zurich", place.getDescription());
        Assert.assertEquals(47.22, place.getLatitude(),0);
        Assert.assertEquals(8.32, place.getLongitude(),0);
    }

    @Test
    @Transactional
    public void testFindById() {
        Place place= placeDao.findById(1L);
        Assert.assertEquals("Europe's largest clock face is located in Zurich", place.getDescription());
        Assert.assertEquals(47.22, place.getLatitude(),0);
        Assert.assertEquals(8.32, place.getLongitude(),0);
    }

    @Test
    @Transactional
    public void testAddAndGetPlaceToFromContact() {
        Place place = placeDao.findById(1L);
        placeDao.addPlaceToContact(place, "user1");
        List<PlaceDto> places = placeDao.getAllContactPlaces("user1");
        Assert.assertEquals(1, places.size());
    }

    @Test
    @Transactional
    public void testHasPlace() {
        Place place = placeDao.findById(1L);
        placeDao.addPlaceToContact(place, "user1");
        Assert.assertTrue(placeDao.hasPlace("Zurich","user1"));
        Assert.assertFalse(placeDao.hasPlace("test","user1"));
    }
}

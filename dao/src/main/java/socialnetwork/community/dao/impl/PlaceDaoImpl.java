package socialnetwork.community.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.PlaceDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.PlaceDao;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.Place;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static socialnetwork.community.dao.converters.EntityConverter.convert;


@Repository
@Transactional(readOnly = true)
public class PlaceDaoImpl implements PlaceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ContactDao contactDao;

    @Transactional(readOnly = false)
    public long addPlace(PlaceDto placeDto) {
        Place place = convert(placeDto);
        sessionFactory.getCurrentSession().save(place);
        sessionFactory.getCurrentSession().flush();
        return place.getId();
    }

    public List<PlaceDto> getAllPlaces() {
        List<Place> places = sessionFactory.getCurrentSession().createQuery("from Place").list();
        List<PlaceDto> result = new ArrayList<PlaceDto>(places.size());
        for (Place place : places) {
            result.add(convert(place));
        }
        return result;
    }

    public Place findByTitle(String title) {
        List<Place> places = sessionFactory.getCurrentSession().createQuery("select p from Place p where p.title = :title").setParameter("title", title).list();
        if (places.isEmpty()) {
            return null;
        } else {
            return places.get(0);
        }
    }

    public Place findById(long id) {
        List<Place> places = sessionFactory.getCurrentSession().createQuery("select h from Place h where h.id = :id").setParameter("id", id).list();
        if (places.isEmpty()) {
            return null;
        } else {
            return places.get(0);
        }
    }

    @Transactional(readOnly = false)
    public void addPlaceToContact(Place place, String username) {
        Contact contact = contactDao.findByUsername(username);
        contact.addPlace(place);
        sessionFactory.getCurrentSession().flush();
    }

    public List<PlaceDto> getAllContactPlaces(String username) {
        List<PlaceDto> result = new ArrayList<>();
        Contact contact = contactDao.findByUsername(username);
        for (Place place : contact.getPlaces()) {
            result.add(convert(place));
        }
        return result;
    }

    public boolean hasPlace(String title, String username) {
        List<PlaceDto> places = getAllContactPlaces(username);
        for (PlaceDto place : places) {
            if (place.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}

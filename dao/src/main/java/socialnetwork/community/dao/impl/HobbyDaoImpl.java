package socialnetwork.community.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.HobbyDao;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.Hobby;

import java.util.*;

import static socialnetwork.community.dao.converters.EntityConverter.convert;


@Repository
@Transactional(readOnly = true)
public class HobbyDaoImpl implements HobbyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ContactDao contactDao;

    @Transactional(readOnly = false)
    public long addHobby(HobbyDto hobbyDto) {
        Hobby hobby = convert(hobbyDto);
        sessionFactory.getCurrentSession().save(hobby);
        sessionFactory.getCurrentSession().flush();
        return hobby.getId();
    }

    public List<HobbyDto> getAllHobbies() {
        List<Hobby> hobbies = sessionFactory.getCurrentSession().createQuery("from Hobby").list();
        List<HobbyDto> result = new ArrayList<HobbyDto>(hobbies.size());
        for (Hobby hobby : hobbies) {
            result.add(convert(hobby));
        }
        return result;
    }

    public List<Hobby> findByTitle(String title) {
        List<Hobby> hobbies = sessionFactory.getCurrentSession().createQuery("select h from Hobby h where h.title = :title").setParameter("title", title).list();
        return hobbies;
    }

    public Hobby findById(long id) {
        List<Hobby> hobbies = sessionFactory.getCurrentSession().createQuery("select h from Hobby h where h.id = :id").setParameter("id", id).list();
        if (hobbies.isEmpty()) {
            return null;
        } else {
            return hobbies.get(0);
        }
    }

    public List<HobbyDto> getAllContactHobbies(String username) {
        List<HobbyDto> result = new ArrayList<>();
        Contact contact = contactDao.findByUsername(username);
        for (Hobby hobby : contact.getHobbies()) {
            result.add(convert(hobby));
        }
        return result;
    }

    @Transactional(readOnly = false)
    public void addHobbyToContact(Hobby hobby, String username) {
        Contact contact = contactDao.findByUsername(username);
        contact.addHobby(hobby);
        sessionFactory.getCurrentSession().flush();
    }

    public boolean hasHobby(String title, String username) {
        List<HobbyDto> hobbies = getAllContactHobbies(username);
        for (HobbyDto hobby : hobbies) {
            if (hobby.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

}

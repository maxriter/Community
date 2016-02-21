package socialnetwork.community.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.PostDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.PostDao;
import socialnetwork.community.dao.entity.Post;

import java.util.ArrayList;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@Repository
@Transactional(readOnly = true)
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ContactDao contactDao;

    public List<PostDto> convertPostList(List<Post> list) {
        List<PostDto> result = new ArrayList<>();
        for (Post post : list) {
            result.add(convert(post));
        }
        return result;
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = sessionFactory.getCurrentSession().createQuery("from Post").list();
        List<PostDto> result = new ArrayList<PostDto>(posts.size());
        for (Post post : posts) {
            result.add(convert(post));
        }
        return result;
    }

    public List<PostDto> getFriendsPosts(String username) {
        List<ContactDto> friends = contactDao.getAllFriendsByContact(username);
        List ids = new ArrayList<>();
        List<PostDto> result = new ArrayList<>();
        for (ContactDto contactDto : friends) {
            ids.add(contactDto.getId());
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Post where contact_id in (:ids)");
        query.setParameterList("ids", ids);
        List<Post> posts = query.list();
        for (Post post : posts) {
            result.add(convert(post));
        }
        return result;
    }

    public List<PostDto> getPostsByContact(Long id) {
        List<Post> messages = sessionFactory.getCurrentSession().createQuery("from Post where contact_id = :id").setParameter("id", id).list();
        return convertPostList(messages);
    }

}

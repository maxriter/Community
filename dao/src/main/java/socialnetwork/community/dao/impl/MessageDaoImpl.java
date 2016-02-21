package socialnetwork.community.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.MessageDto;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.MessageDao;
import socialnetwork.community.dao.entity.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@Repository
@Transactional(readOnly = true)
public class MessageDaoImpl implements MessageDao, Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    ContactDao contactDao;

    @Transactional(readOnly = false)
    public long storeMessage(MessageDto messageDto) {
        Message message = convert(messageDto);
        sessionFactory.getCurrentSession().save(message);
        sessionFactory.getCurrentSession().flush();
        return message.getId();
    }

    public List<MessageDto> convertMessageList(List<Message> list) {
        List<MessageDto> result = new ArrayList<>();
        for (Message message : list) {
            result.add(convert(message));
        }
        return result;
    }

    public List<MessageDto> getConversation(ContactDto to, ContactDto from) {
        long contactTo = to.getId();
        long contactFrom = from.getId();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Message where (contact_id_from=:contactTo and contact_id_to=:contactFrom) or (contact_id_from=:contactFrom and contact_id_to=:contactTo)").setParameter("contactTo", contactTo).setParameter("contactFrom", contactFrom);
        List<Message> messages = query.list();
        List<MessageDto> result = convertMessageList(messages);
        return result;
    }

    public Message findById(long id) {
        List<Message> messages = sessionFactory.getCurrentSession().createQuery("select m from Message m where m.id = :id").setParameter("id", id).list();
        if (messages.isEmpty()) {
            return null;
        } else {
            return messages.get(0);
        }
    }

    public List<MessageDto> getSend(ContactDto contactDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Message where contact_id_from = :id").setParameter("id", contactDto.getId());
        List<Message> messages = query.list();
        for (Message message : messages) {
            StringBuilder sb = new StringBuilder();
            sb.append(contactDao.findById(message.getContact_to()).getFirstName());
            sb.append("  ");
            sb.append(contactDao.findById(message.getContact_to()).getLastName());
            message.setRecipient(sb.toString());
        }
        return convertMessageList(messages);
    }

    public List<MessageDto> getReceived(ContactDto contactDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Message where contact_id_to = :id").setParameter("id", contactDto.getId());
        List<Message> messages = query.list();
        for (Message message : messages) {
            StringBuilder sb = new StringBuilder();
            sb.append(contactDao.findById(message.getContact_from()).getFirstName());
            sb.append("  ");
            sb.append(contactDao.findById(message.getContact_from()).getLastName());
            message.setSender(sb.toString());
        }
        return convertMessageList(messages);
    }
}

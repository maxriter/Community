package socialnetwork.community.dao;


import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.MessageDto;
import socialnetwork.community.dao.entity.Message;

import java.util.List;


public interface MessageDao {
    long storeMessage(MessageDto messageDto);
    List<MessageDto> getConversation(ContactDto a, ContactDto b);
    Message findById(long id);
    List<MessageDto> convertMessageList(List<Message> list);
    List<MessageDto> getSend(ContactDto contactDto);
    List<MessageDto> getReceived(ContactDto contactDto);
}

package socialnetwork.community.dao;

import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.api.model.PostDto;
import socialnetwork.community.api.model.filter.ContactFilter;
import socialnetwork.community.dao.entity.Contact;

import java.util.List;

public interface ContactDao {

    long addContact(ContactDto contactDto);

    ContactDto addFriendship(ContactDto a, ContactDto b);

    ContactDto removeFriendship(ContactDto a, ContactDto b);

    List<ContactDto> getAllContacts();

    List<ContactDto> getFriendship(ContactDto a);

    Contact findById(int id);

    void save(Contact contact);

    Contact findByUsername(String username);

    ContactDto editContactProfile(ContactDto contactDto, String username);

    boolean addHobbyToContact(HobbyDto hobbyDto, String username);

    PostDto addPostToContact(PostDto postDto, String username);

    List<ContactDto> getAllFriendsByContact(String username);

    List<ContactDto> findByParam(String param, String username);

    ContactDto findById(Long id);

    boolean isUsernameExists(String username);
}

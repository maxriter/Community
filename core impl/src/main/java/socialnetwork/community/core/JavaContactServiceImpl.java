package socialnetwork.community.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.api.JavaContactService;
import socialnetwork.community.api.model.*;
import socialnetwork.community.dao.*;
import socialnetwork.community.dao.entity.Hobby;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static socialnetwork.community.dao.converters.EntityConverter.convert;

@Service
@Transactional
public class JavaContactServiceImpl implements JavaContactService, Serializable {

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private HobbyDao hobbyDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private PostDao postDao;


    @Override
    public long addContact(ContactDto contactDto) {
        return contactDao.addContact(contactDto);
    }

    @Override
    public ContactDto addFriendship(ContactDto a, ContactDto b) {
        return contactDao.addFriendship(a, b);
    }

    @Override
    public ContactDto removeFriendship(ContactDto a, ContactDto b) {
        return contactDao.removeFriendship(a, b);
    }

    @Override
    public List<ContactDto> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Override
    public List<ContactDto> getFriendship(ContactDto a) {
        return contactDao.getFriendship(a);
    }

    @Override
    public ContactDto findContactById(int id) {
        return convert(contactDao.findById(id));
    }

    @Override
    public void saveContact(ContactDto contact) {
        contactDao.save(convert(contact));
    }

    @Override
    public ContactDto findContactByUsername(String username) {
        return convert(contactDao.findByUsername(username));
    }

    @Override
    public ContactDto editContactProfile(ContactDto contactDto, String username) {
        return contactDao.editContactProfile(contactDto, username);
    }

    @Override
    public boolean addHobbyToContact(HobbyDto hobbyDto, String username) {
        return contactDao.addHobbyToContact(hobbyDto, username);
    }

    @Override
    public PostDto addPostToContact(PostDto postDto, String username) {
        return contactDao.addPostToContact(postDto, username);
    }

    @Override
    public List<ContactDto> getAllFriendsByContact(String username) {
        return contactDao.getAllFriendsByContact(username);
    }

    @Override
    public List<ContactDto> findContactsByParam(String param, String username) {
        return contactDao.findByParam(param, username);
    }

    @Override
    public ContactDto findContactById(Long id) {
        return contactDao.findById(id);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return contactDao.isUsernameExists(username);
    }

    @Override
    public long addHobby(HobbyDto hobbyDto) {
        return hobbyDao.addHobby(hobbyDto);
    }

    @Override
    public List<HobbyDto> getAllHobbies() {
        return hobbyDao.getAllHobbies();
    }

    @Override
    public List<HobbyDto> findHobbyByTitle(String title) {
        List<Hobby> hobbies = hobbyDao.findByTitle(title);
        List<HobbyDto> res = new ArrayList<>();
        for (Hobby hobby : hobbies) {
            res.add(convert(hobby));
        }
        return res;
    }

    @Override
    public HobbyDto findHobbyById(long id) {
        return convert(hobbyDao.findById(id));
    }

    @Override
    public List<HobbyDto> getAllContactHobbies(String username) {
        return hobbyDao.getAllContactHobbies(username);
    }

    @Override
    public long storeMessage(MessageDto messageDto) {
        return messageDao.storeMessage(messageDto);
    }

    @Override
    public List<MessageDto> getConversation(ContactDto a, ContactDto b) {
        return messageDao.getConversation(a, b);
    }

    @Override
    public MessageDto findMessageById(long id) {
        return convert(messageDao.findById(id));
    }

    @Override
    public List<MessageDto> getSendMessages(ContactDto contactDto) {
        return messageDao.getSend(contactDto);
    }

    @Override
    public List<MessageDto> getReceivedMessages(ContactDto contactDto) {
        return messageDao.getReceived(contactDto);
    }

    @Override
    public long addPlace(PlaceDto placeDto) {
        return placeDao.addPlace(placeDto);
    }

    @Override
    public PlaceDto findPlaceByTitle(String title) {
        return convert(placeDao.findByTitle(title));
    }

    @Override
    public void addPlaceToContact(PlaceDto place, String username) {
        placeDao.addPlaceToContact(convert(place), username);
    }

    @Override
    public PlaceDto findPlaceById(long id) {
        return convert(placeDao.findById(id));
    }

    @Override
    public List<PlaceDto> getAllPlaces() {
        return placeDao.getAllPlaces();
    }

    @Override
    public List<PlaceDto> getAllContactPlaces(String username) {
        return placeDao.getAllContactPlaces(username);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public List<PostDto> getPostsByContact(Long id) {
        return postDao.getPostsByContact(id);
    }

    @Override
    public List<PostDto> getFriendsPosts(String username) {
        return postDao.getFriendsPosts(username);
    }

    @Override
    public void save(ContactDto contact) {
        contactDao.save(convert(contact));
    }

    @Override
    public boolean hasHobby(String title, String username) {
        return hobbyDao.hasHobby(title, username);
    }

    @Override
    public boolean hasPlace(String title, String username) {
        return placeDao.hasPlace(title, username);
    }
}

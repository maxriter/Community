package socialnetwork.community.api;

import socialnetwork.community.api.model.*;

import java.time.LocalDate;
import java.util.List;

public interface JavaContactService {

    long addContact(ContactDto contactDto);

    ContactDto addFriendship(ContactDto a, ContactDto b);

    ContactDto removeFriendship(ContactDto a, ContactDto b);

    List<ContactDto> getAllContacts();

    List<ContactDto> getFriendship(ContactDto a);

    ContactDto findContactById(int id);

    void saveContact(ContactDto contact);

    ContactDto findContactByUsername(String username);

    ContactDto editContactProfile(ContactDto contactDto, String username);

    boolean addHobbyToContact(HobbyDto hobbyDto, String username);

    PostDto addPostToContact(PostDto postDto, String username);

    List<ContactDto> getAllFriendsByContact(String username);

    List<ContactDto> findContactsByParam(String param, String username);

    ContactDto findContactById(Long id);

    boolean isUsernameExists(String username);

    long addHobby(HobbyDto hobbyDto);
    List<HobbyDto> getAllHobbies();
    List<HobbyDto> findHobbyByTitle(String title);
    HobbyDto findHobbyById(long id);
    List<HobbyDto> getAllContactHobbies(String username);

    long storeMessage(MessageDto messageDto);
    List<MessageDto> getConversation(ContactDto a, ContactDto b);
    MessageDto findMessageById(long id);
    List<MessageDto> getSendMessages(ContactDto contactDto);
    List<MessageDto> getReceivedMessages(ContactDto contactDto);

    long addPlace(PlaceDto placeDto);
    PlaceDto findPlaceByTitle(String title);
    void addPlaceToContact(PlaceDto place, String username);
    PlaceDto findPlaceById(long id);
    List<PlaceDto> getAllPlaces();
    List<PlaceDto> getAllContactPlaces(String username);

    List<PostDto> getAllPosts();

    List<PostDto> getPostsByContact(Long id);

    List<PostDto> getFriendsPosts(String username);

    void save(ContactDto contact);

    boolean hasHobby(String title, String username);

    boolean hasPlace(String title, String username);


}

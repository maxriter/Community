package socialnetwork.community.dao.converters;


import socialnetwork.community.api.model.*;
import socialnetwork.community.dao.entity.*;

public class EntityConverter {

    private EntityConverter() {
    }

    public static Contact convert(ContactDto contactDto) {
        if (contactDto == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setUsername(contactDto.getUsername());
        contact.setPassword(contactDto.getPassword());
        contact.setBirthDate(contactDto.getBirthDate());
        contact.setPhone(contactDto.getPhone());
        return contact;
    }

    public static ContactDto convert(Contact contact) {
        if (contact == null) {
            return null;
        }
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setUsername(contact.getUsername());
        contactDto.setPassword(contact.getPassword());
        contactDto.setBirthDate(contact.getBirthDate());
        contactDto.setPhone(contact.getPhone());
        return contactDto;
    }

    public static Message convert(MessageDto messageDto) {
        if (messageDto == null) {
            return null;
        }
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setMessage_date(messageDto.getMessage_date());
        message.setContact_from(messageDto.getContact_from());
        message.setContact_to(messageDto.getContact_to());
        message.setContent(messageDto.getContent());
        message.setSender(messageDto.getSender());
        message.setRecipient(messageDto.getRecipient());
        return message;
    }

    public static MessageDto convert(Message message) {
        if (message == null) {
            return null;
        }
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessage_date(message.getMessage_date());
        messageDto.setContact_from(message.getContact_from());
        messageDto.setContact_to(message.getContact_to());
        messageDto.setContent(message.getContent());
        messageDto.setSender(message.getSender());
        messageDto.setRecipient(message.getRecipient());
        return messageDto;
    }

    public static HobbyDto convert(Hobby hobby) {
        if (hobby == null) {
            return null;
        }
        HobbyDto hobbyDto = new HobbyDto();
        hobbyDto.setId(hobby.getId());
        hobbyDto.setTitle(hobby.getTitle());
        hobbyDto.setDescription(hobby.getDescription());
        return hobbyDto;
    }

    public static Hobby convert(HobbyDto hobbyDto) {
        if (hobbyDto == null) {
            return null;
        }
        Hobby hobby = new Hobby();
        hobby.setId(hobbyDto.getId());
        hobby.setTitle(hobbyDto.getTitle());
        hobby.setDescription(hobbyDto.getDescription());
        return hobby;
    }

    public static Place convert(PlaceDto placeDto) {
        if (placeDto == null) {
            return null;
        }
        Place place = new Place();
        place.setId(placeDto.getId());
        place.setDescription(placeDto.getDescription());
        place.setTitle(placeDto.getTitle());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        return place;
    }

    public static PlaceDto convert(Place place) {
        if (place == null) {
            return null;
        }
        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(place.getId());
        placeDto.setDescription(place.getDescription());
        placeDto.setTitle(place.getTitle());
        placeDto.setLatitude(place.getLatitude());
        placeDto.setLongitude(place.getLongitude());
        return placeDto;
    }

    public static Post convert(PostDto postDto) {
        if (postDto == null) {
            return null;
        }
        Post post = new Post();
        post.setId(postDto.getId());
        post.setPost_date(postDto.getPost_date());
        post.setContent(postDto.getContent());
        post.setContact(convert(postDto.getContactDto()));
        return post;
    }

    public static PostDto convert(Post post) {
        if (post == null) {
            return null;
        }
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPost_date(post.getPost_date());
        postDto.setContent(post.getContent());
        postDto.setContactDto(convert(post.getContact()));
        return postDto;
    }
}

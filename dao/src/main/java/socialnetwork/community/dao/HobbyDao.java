package socialnetwork.community.dao;


import socialnetwork.community.api.model.HobbyDto;
import socialnetwork.community.dao.entity.Hobby;

import java.util.List;



public interface HobbyDao {
    long addHobby(HobbyDto hobbyDto);
    List<HobbyDto> getAllHobbies();
    List<Hobby> findByTitle(String title);
    Hobby findById(long id);
    List<HobbyDto> getAllContactHobbies(String username);
    void addHobbyToContact(Hobby hobby, String username);
    boolean hasHobby(String title, String username);
}

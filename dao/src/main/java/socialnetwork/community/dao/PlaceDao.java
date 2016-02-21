package socialnetwork.community.dao;

import socialnetwork.community.api.model.ContactDto;
import socialnetwork.community.api.model.PlaceDto;
import socialnetwork.community.dao.entity.Place;

import java.util.List;
import java.util.Set;

public interface PlaceDao {

    long addPlace(PlaceDto placeDto);
    Place findByTitle(String title);
    void addPlaceToContact(Place place, String username);
    Place findById(long id);
    List<PlaceDto> getAllPlaces();
    List<PlaceDto> getAllContactPlaces(String username);
    boolean hasPlace(String title, String username);
}

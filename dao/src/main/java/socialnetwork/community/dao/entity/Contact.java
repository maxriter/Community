package socialnetwork.community.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_BIRTH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "STATE")
    private String state = State.ACTIVE.getState();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CONTACT_ROLE",
            joinColumns = {@JoinColumn(name = "CONTACT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CONTACT_HOBBY",
            inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"),
            joinColumns = @JoinColumn(name = "CONTACT_ID")
    )
    private Set<Hobby> hobbies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CONTACT_PLACE",
            inverseJoinColumns = @JoinColumn(name = "PLACE_ID"),
            joinColumns = @JoinColumn(name = "CONTACT_ID")
    )
    private Set<Place> places;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FRIENDSHIP", joinColumns = @JoinColumn(name = "CONTACT_ID_MAIN"),
            inverseJoinColumns = @JoinColumn(name = "CONTACT_ID_FRIEND"))
    private Set<Contact> friendship = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL)
    private Set<Post> posts;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Contact> getFriendship() {
        return friendship;
    }

    public void addFriend(Contact contact) {
        friendship.add(contact);
    }

    public void removeFriend(Contact contact) {
        friendship.remove(contact);
    }

    public void addHobby(Hobby hobby) {
        hobbies.add(hobby);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

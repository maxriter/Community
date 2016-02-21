package socialnetwork.community.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ContactDto implements Serializable, Comparable {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private LocalDate birthDate;

    private String phone;

    public ContactDto() {
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDto contactDto = (ContactDto) o;
        return Objects.equals(id, contactDto.id) &&
                Objects.equals(firstName, contactDto.firstName) &&
                Objects.equals(lastName, contactDto.lastName) &&
                Objects.equals(birthDate, contactDto.birthDate) &&
                Objects.equals(birthDate, contactDto.phone)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, phone);
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        ContactDto contactDto = (ContactDto) o;
        return contactDto.getId() < getId() ? -1 : contactDto.getId() == getId() ? 0 : 1;
    }
}

package socialnetwork.community.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class PostDto implements Serializable {
    private Long id;
    private String content;
    private LocalDateTime post_date;
    private ContactDto contactDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }

    public ContactDto getContactDto() {
        return contactDto;
    }

    public void setContactDto(ContactDto contactDto) {
        this.contactDto = contactDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostDto postDto = (PostDto) o;

        if (id != null ? !id.equals(postDto.id) : postDto.id != null) return false;
        if (content != null ? !content.equals(postDto.content) : postDto.content != null) return false;
        if (post_date != null ? !post_date.equals(postDto.post_date) : postDto.post_date != null) return false;
        return !(contactDto != null ? !contactDto.equals(postDto.contactDto) : postDto.contactDto != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (post_date != null ? post_date.hashCode() : 0);
        result = 31 * result + (contactDto != null ? contactDto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", post_date=" + post_date +
                ", contactDto=" + contactDto +
                '}';
    }
}




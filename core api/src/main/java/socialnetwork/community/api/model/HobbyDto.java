package socialnetwork.community.api.model;

import java.io.Serializable;

public class HobbyDto implements Serializable,Comparable {
    private Long id;
    private String title;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HobbyDto hobbyDto = (HobbyDto) o;

        if (title != null ? !title.equals(hobbyDto.title) : hobbyDto.title != null) return false;
        return !(description != null ? !description.equals(hobbyDto.description) : hobbyDto.description != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HobbyDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        HobbyDto hobbyDto = (HobbyDto) o;
        return hobbyDto.getId() < getId() ? -1 : hobbyDto.getId() == getId() ? 0 : 1;
    }
}

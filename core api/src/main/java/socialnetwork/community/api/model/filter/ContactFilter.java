package socialnetwork.community.api.model.filter;

import java.time.LocalDate;


public class ContactFilter {
    private LocalDate birthDateFrom;
    private LocalDate birthDateTo;

    public LocalDate getBirthDateTo() {
        return birthDateTo;
    }

    public void setBirthDateTo(LocalDate birthDateTo) {
        this.birthDateTo = birthDateTo;
    }

    public LocalDate getBirthDateFrom() {
        return birthDateFrom;
    }

    public void setBirthDateFrom(LocalDate birthDateFrom) {
        this.birthDateFrom = birthDateFrom;
    }

    @Override
    public String toString() {
        return "ContactFilter{" +
                "birthDateFrom=" + birthDateFrom +
                ", birthDateTo=" + birthDateTo +
                '}';
    }
}

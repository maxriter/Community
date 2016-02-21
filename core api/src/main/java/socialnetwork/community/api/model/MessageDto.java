package socialnetwork.community.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageDto implements Serializable {

    private Long id;
    private LocalDateTime message_date;
    private Long contact_from;
    private Long contact_to;
    private String content;
    private String sender;
    private String recipient;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getMessage_date() {
        return message_date;
    }

    public void setMessage_date(LocalDateTime message_date) {
        this.message_date = message_date;
    }

    public Long getContact_from() {
        return contact_from;
    }

    public void setContact_from(Long contact_from) {
        this.contact_from = contact_from;
    }

    public Long getContact_to() {
        return contact_to;
    }

    public void setContact_to(Long contact_to) {
        this.contact_to = contact_to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", message_date=" + message_date +
                ", contact_from=" + contact_from +
                ", contact_to=" + contact_to +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}

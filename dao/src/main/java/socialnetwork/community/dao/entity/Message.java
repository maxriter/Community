package socialnetwork.community.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="MESSAGE_DATE")
    private LocalDateTime message_date;

    @Column(name="CONTACT_ID_FROM")
    private Long contact_from;

    @Column(name="CONTACT_ID_TO")
    private Long contact_to;

    @Column(name="CONTENT")
    private String content;

    @Transient
    private String sender;

    @Transient
    private String recipient;

    public Message() {
    }

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
        return "Message{" +
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


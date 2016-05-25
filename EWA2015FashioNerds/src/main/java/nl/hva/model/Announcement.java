package nl.hva.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * An announcement is an effective way for admins to communicate to all users; a short message will
 * show up on everyone's login screen. Don't forget to delete the announcement after it becomes
 * irrelevant as you don't want to clutter the login screen with too many announcements.
 * 
 * @version 1.0
 * @since 02-01-2016
 * @author Floris van Lent
 */
@Entity(name = "Announcement")
public class Announcement extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private int announcementID;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String message;

    public Announcement() {
    }

    public Announcement(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public Announcement(int announcementID, String title, String message) {
        this.announcementID = announcementID;
        this.title = title;
        this.message = message;
    }

    @Override
    public Integer getId() {
        return announcementID;
    }

    public int getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(int announcementID) {
        this.announcementID = announcementID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isNew() {
        return (announcementID == 0);
    }
}

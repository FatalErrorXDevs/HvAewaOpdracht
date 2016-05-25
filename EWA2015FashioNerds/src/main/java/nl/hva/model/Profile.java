package nl.hva.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import nl.hva.gravatar.Gravatar;
import nl.hva.gravatar.GravatarDefaultImage;
import nl.hva.gravatar.GravatarRating;

/**
 * A profile is linked to an user. The profile stores optional personal information so
 * team-members in your projects can learn a little bit more about you. You can save a nickname,
 * your full name, your age, the organisation you work at, the location of that organisation, your
 * work-function there, your phone number, your e-mailaddress and a bio where you can post a short
 * story about yourself. The e-mailaddress you save will also link to your Gravatar if you have one.
 *
 * @version 1.0
 * @since 04-01-2016
 * @author Floris van Lent
 */
@Entity(name = "Profiles")
public class Profile implements Serializable {

    @Id
    @GeneratedValue
    private int profileID;

    @NotNull
    @OneToOne
    private User account;

    private String nickname;
    private String fullName;
    private int age;
    private String organisation;
    private String location;
    private String workFunction;
    private String phoneNumber;
    private String emailAddress;
    private String bio;

    public Profile() {
    }

    public Profile(int profileID, User account) {
        this(profileID, account, "", "", 0, "", "", "", "", "", "");
    }

    public Profile(int profileID, User account, String nickname, String fullName, int age,
        String bio, String location, String organisation, String workFunction, String phoneNumber,
        String emailAddress) {
        this.profileID = profileID;
        this.account = account;
        this.nickname = nickname;
        this.fullName = fullName;
        this.age = age;
        this.bio = bio;
        this.location = location;
        this.organisation = organisation;
        this.workFunction = workFunction;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getWorkFunction() {
        return workFunction;
    }

    public void setWorkFunction(String workFunction) {
        this.workFunction = workFunction;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Uses the Open Source Gravatar files from nl.hva.gravatar to get a URL with the gravatar-image
     * that's paired with the e-mailaddress in class-variable emailAddress.
     *
     * @return a URL that displays the Gravatar
     */
    public String getGravatar() {
        String email = emailAddress;
        if (emailAddress == null || emailAddress.isEmpty()) {
            email = "fashionerds@bekodesign.com";
        }
        Gravatar gravatar = new Gravatar();
        gravatar.setSize(200);
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
        return gravatar.getUrl(email);
    }
}

package nl.hva.model;

import nl.hva.model.fabric.Fabric;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany; 
import javax.persistence.OneToOne; 
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int Id;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    private String firstName;
    private String infix;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserRole> userRole = new ArrayList<>();

    private Locale Language;
    private boolean requirePasswordChange = true;

    @OneToOne(mappedBy="account")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Profile profile;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Fabric> favoriteFabrics = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL,mappedBy="workedOn")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Fabric> workedOn = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "ProjectAccounts", joinColumns = {
        @JoinColumn(name = "AccountID")},
            inverseJoinColumns = {
                @JoinColumn(name = "ProjectID")}
    )

    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public User() {
    }

    public User(int id, String username, String password, boolean enabled) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(int id, String username, String password, boolean enabled, List<UserRole> userRole) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    public int getId() {
        return Id;
    }

    public boolean freshAccount() {
        return (this.Id == 0);
    }

    public boolean hasPassword() {
        return password != null && !password.isEmpty();
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Locale getLanguage() {
        return Language;
    }

    public void setLanguage(Locale Language) {
        this.Language = Language;
    }

    public boolean isRequirePasswordChange() {
        return requirePasswordChange;
    }

    public void setRequirePasswordChange(boolean requirePasswordChange) {
        this.requirePasswordChange = requirePasswordChange;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String role() {
        return this.userRole.iterator().next().getRole();
    }

    public List<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRole> userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Fabric> getFavoriteFabrics() {
        return favoriteFabrics;
    }

    public void setFavoriteFabrics(List<Fabric> favoriteFabrics) {
        this.favoriteFabrics = favoriteFabrics;
    }

    public void addFavorite(Fabric fabric) {
        favoriteFabrics.add(fabric);
    }

    public void removeFavorite(Fabric fabric) {
        int i = 0;
        for (Fabric f : favoriteFabrics) {
            if (f.getId() == fabric.getId()) {
                favoriteFabrics.remove(i);
                break;
            }
            i++;
        }
    }

    public String getName() {
        return firstName
            + ((infix == null) ? "" : " " + infix)
            + " " + lastName;
    }

}

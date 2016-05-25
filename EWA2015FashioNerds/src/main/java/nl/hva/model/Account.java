package nl.hva.model;

import nl.hva.model.fabric.Fabric;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @version 1.0
 * @since 01-10-2015
 * @author Bert Kooij, Tom Prins
 */
@Entity(name = "Users")
public class Account implements Serializable {

    public Account() {
    }

    public Account(int id, String userName, String password, String rights) {
        this.Id = id;
        this.GebruikersNaam = userName;
        this.Password = password;
        this.Recht_Naam = rights;
    }

    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    private String GebruikersNaam;

    @NotNull
    private String Password;

    @NotNull
    private String Recht_Naam;

    private Locale Language;

    private boolean requirePasswordChange = true;

    public boolean isNew() {
        return (this.Id == 0);
    }

    public boolean hasPassword() {
        return Password != null && !Password.isEmpty();
    }

    public String getGebruikersNaam() {
        return GebruikersNaam;
    }

    public void setGebruikersNaam(String GebruikersNaam) {
        this.GebruikersNaam = GebruikersNaam;
    }

    public void setRecht_Naam(String Recht_Naam) {
        this.Recht_Naam = Recht_Naam;
    }

    public boolean isRequirePasswordChange() {
        return requirePasswordChange;
    }

    public void setRequirePasswordChange(boolean requirePasswordChange) {
        this.requirePasswordChange = requirePasswordChange;
    }

    /**
     * Get the value of naam
     *
     * @return the value of naam
     */
    public String getUserName() {
        return GebruikersNaam;
    }

    /**
     * Set the value of naam
     *
     * @param userName new value of naam
     */
    public void setUserName(String userName) {
        this.GebruikersNaam = userName;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public String getRecht_Naam() {
        return Recht_Naam;
    }

    public Locale getLanguage() {
        return Language;
    }

    public void setLanguage(Locale Language) {
        this.Language = Language;
    }

    @ManyToMany()
    @JoinTable(name = "FavoriteFabrics", joinColumns = {
        @JoinColumn(name = "AccountId")},
            inverseJoinColumns = {
                @JoinColumn(name = "FabricID")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Fabric> favoriteFabrics;

    public List<Fabric> getFavoriteFabrics() {
        return favoriteFabrics;
    }

    public void setFavoriteFabrics(List<Fabric> favoriteFabrics) {
        this.favoriteFabrics = favoriteFabrics;
    }
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

    @Override
    public String toString() {
        return "Account{" + "Id=" + Id + ", GebruikersNaam=" + GebruikersNaam + ", Password=" + Password + ", Recht_Naam=" + Recht_Naam + ", Language=" + Language + ", requirePasswordChange=" + requirePasswordChange + ", favoriteFabrics=" + favoriteFabrics + '}';
    }

}

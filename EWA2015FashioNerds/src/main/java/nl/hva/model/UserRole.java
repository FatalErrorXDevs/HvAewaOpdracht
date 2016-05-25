package nl.hva.model;

/**
 *
 * @author Tom Prins
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class UserRole implements Serializable {

    @Id
    @GeneratedValue
    private Integer userRoleId;
    
    @ManyToMany(mappedBy = "userRole")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> user = new ArrayList<>();
    
    @Column(nullable = false, length = 45,unique = true)
    private String role;    
    
    
    public UserRole() {
    }
    
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
    
    public void addUser(User user)
    {
        this.user.add(user);
    }
    
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" + "role=" + role + '}';
    }

}

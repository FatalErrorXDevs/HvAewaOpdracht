package nl.hva.model;

import nl.hva.model.fabricdata.completeness.CalculatorImpl;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;

/**
 *
 * @author Bert
 */
@MappedSuperclass
public abstract class AbstractEntity extends CalculatorImpl implements Auditable<User, Integer>, ModelInterface {
    
    @CreatedDate
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate = new DateTime();

    @LastModifiedDate
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModifiedDate = new DateTime();

    @CreatedBy
    @ManyToOne
    @JoinColumn
    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn
    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    private User lastModifiedBy;

    @Override
    public User getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(User u) {
        createdBy = u;
    }

    @Override
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(DateTime dt) {
        createdDate = dt;
    }

    @Override
    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(User u) {
        lastModifiedBy = u;
    }

    @Override
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(DateTime dt) {
        lastModifiedDate = dt;
    }

    @Override
    public void beforeUpdate()
    {
        setLastModifiedDate(new DateTime());
    }

    @Override
    public void beforeFirstSave()
    {
        
    }
    
    

}

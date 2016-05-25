package nl.hva.dao;

import nl.hva.model.Profile;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @since 03-11-2015
 * @author Floris van Lent
 */
@Repository
public class ProfileDAO extends AbstractDAO {

    private String hql;
    private Query query;

    public Profile getProfileByProfileID(int id) {
        hql = "FROM Profiles WHERE profileID = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        Profile profile = (Profile) query.list().get(0);
        return profile;
    }

    public void saveProfile(Profile profile) {
        getCurrentSession().save(profile);
    }

    public void updateProfile(Profile profile) {
        getCurrentSession().update(profile);
    }
}

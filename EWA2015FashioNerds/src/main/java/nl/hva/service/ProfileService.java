package nl.hva.service;

import nl.hva.dao.ProfileDAO;
import nl.hva.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.0
 * @since 05-11-2015
 * @author Floris van Lent
 */
@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    public Profile getProfileByProfileID(int id) {
        return profileDAO.getProfileByProfileID(id);
    }

    public void updateProfile(Profile profile) {
        profileDAO.updateProfile(profile);
    }

    public void saveProfile(Profile profile) {
        profileDAO.saveProfile(profile);
    }
}

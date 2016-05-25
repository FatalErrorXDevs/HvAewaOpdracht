package nl.hva.service;

import java.util.List;
import nl.hva.dao.AnnouncementDAO;
import nl.hva.model.Announcement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @since 03-01-2016
 * @author Floris van Lent
 */
@Service
@Transactional
@Scope("singleton")
public class AnnouncementService {

    @Autowired
    private AnnouncementDAO announcementDAO;
    
    public List<Announcement> getAll() {
        return announcementDAO.getAll();
    }
    
    public Announcement getById(int id) {
        return announcementDAO.getById(id);
    }
    
    public void save(Announcement announcement) {
        announcementDAO.save(announcement);
    }
    
    public void update(Announcement announcement) {
        announcementDAO.update(announcement);
    }

    public void delete(Announcement announcement) {
        announcementDAO.delete(announcement);
    }
}

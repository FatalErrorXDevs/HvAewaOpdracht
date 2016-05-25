package nl.hva.service.fabric;

import java.util.List;
import nl.hva.dao.TabDAO;
import org.springframework.transaction.annotation.Transactional;
import nl.hva.model.fabric.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yassine
 */
@Service
@Transactional
@Scope("singleton")
public class TabService{

    @Autowired
    private TabDAO tabDAO;
    
    public List<Tab> getAll()
    {
        return tabDAO.getAll();
    }
    
    public Tab getByName(String name)
    {
        return tabDAO.getByName(name);
    }
    
    public Tab getById(int id)
    {
        return tabDAO.getById(id);
    }
    
    public void save(Tab tab)
    {
        tabDAO.save(tab);
    }
    
    public void update(Tab tab)
    {
        tabDAO.update(tab);
    }

    public void delete(Tab tab)
    {
        tabDAO.delete(tab);
    }
}

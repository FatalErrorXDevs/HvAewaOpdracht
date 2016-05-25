package nl.hva.service.fabric;

import java.util.List;
import nl.hva.dao.DataDAO;
import nl.hva.model.fabric.data.Data;
import org.springframework.transaction.annotation.Transactional;
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
public class DataService {

    @Autowired
    private DataDAO dataDAO;
    
    public List<Data> getAll()
    {
        return dataDAO.getAll();
    }
    
    public Data getByName(String name)
    {
        return dataDAO.getByName(name);
    }
    
    public Data getById(int id)
    {
        return dataDAO.getById(id);
    }

    public void saveOrUpdate(Data data) {
        dataDAO.saveOrUpdate(data);
    }

}

package nl.hva.service.fabric;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import nl.hva.dao.FabricDAO;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.filter.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yassine
 */
@Service
@Transactional
public class FabricService{

    @Autowired
    private FabricDAO fabricDAO;

    /**
     *
     * @return
     */
    public List<Fabric> getAllFabrics() {
        return fabricDAO.getAllFabrics();
    }
    
    public List<Fabric> searchMultipleFabrics(FilterModel filterModel)
    {
        return fabricDAO.searchMultipleFabrics(filterModel);
    } 
    
    public int getMaxWeight(int defaultValue)
    {
        return fabricDAO.getMaxWeight(defaultValue);
    }
    
    public int getMax(String field, int defaultValue)
    {
        return fabricDAO.getMax(field, defaultValue);
    }

    public List getFabricsByName(String Name) {
        return fabricDAO.getAllFabrics();
    } 
    
    public Fabric getFabricById(int id) {
        return fabricDAO.getFabricById(id);
    } 
    
    public void save(Fabric fabric) throws Exception
    {
        fabricDAO.save(fabric);
    }
    
    public void update(Fabric f) throws Exception
    {
        fabricDAO.update(f);
    }
    
    public long count()
    {
        return fabricDAO.count();
    }
    
    public List<Fabric> searchFavoriteFabricByName(int accountId, String FabricName) {
        return fabricDAO.searchFavoriteFabricByName(accountId, FabricName);
    }
}
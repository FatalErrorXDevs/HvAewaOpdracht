package nl.hva.controller.fabric;

import java.io.IOException;
import java.util.List;
import nl.hva.controller.SuperController;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.Page;
import nl.hva.model.User;
import nl.hva.service.fabric.CategoryService;
import nl.hva.service.fabric.FabricService;
import nl.hva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller handles everything after /Fabrics/.
 *
 * @version 1.0
 * @since 06-10-2015
 * @author Yassine el Abdellaoui, Ternel Barzey
 */
@Controller
@RequestMapping("/Fabrics/Favorites/")
public class FavoriteFabricController extends SuperController {

    /**
     * URL of List of Fabrics
     */
    private static final String URL_LIST = "/";

    /**
     * URL of Search List of Fabrics
     */
    private static final String URL_SEARCH = "/Search";

    /**
     * URL for editing Fabric
     */
    private static final String URL_ADD = "/{id}/Add/";

    /**
     * URL for deleting Account
     */
    private static final String URL_DELETE = "/{id}/Remove/";
    /**
     * Model location for Listing Fabrics
     */
    private static final String MODEL_LIST = "favoritefabric/FavoriteFabrics";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../menus/FavoriteFabricMenu.jsp");

    @Autowired
    private FabricService fabricService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;

    /**
     * Handler for fabric list.
     *
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_LIST)
    public ModelAndView list() throws IOException {

        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        view.addObject("list", account.getFavoriteFabrics());
        view.addObject("categories", categoryService.getAll());

        return view;
    }

    /**
     * Handler for searching a Favoritefabric.
     *
     * @param FabricName search for
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_SEARCH)
    public ModelAndView search(@RequestParam("s") String FabricName) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        List<Fabric> resultFavoriteFabrics = fabricService.searchFavoriteFabricByName(account.getId(), FabricName);
        view.addObject("list", resultFavoriteFabrics);
        view.addObject("search", FabricName);

        return view;
    }

    /**
     * Handler for adding a favorite to favorite fabric list.
     *
     * @param id FabricID for adding to favorites
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_ADD)
    public ModelAndView edit(@PathVariable int id) throws IOException, Exception {

        User user = getUser();
        //voegt een favoriteFabric uit de database toe aan het object die overeenkomt met de meegegeven Id uit de JSP
        Fabric favoriteFabric = fabricService.getFabricById(id);

        if (!favoriteFabric.hasFavorite(user)) {
            user.addFavorite(favoriteFabric);
            userService.saveOrUpdate(user);
        }
        ModelAndView view = new ModelAndView("redirect:../../../detailsPage/" + favoriteFabric.getId());

        return view;
    }

    /**
     * Handler for deleting a favorite fabric.
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = URL_DELETE)
    public ModelAndView delete(@PathVariable int id) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        //voegt een favoriteFabric uit de database toe aan het object die overeenkomt met de meegegeven Id uit de JSP
        Fabric favoriteFabric = fabricService.getFabricById(id);

        if (favoriteFabric.hasFavorite(account)) {
            account.removeFavorite(favoriteFabric);
            userService.update(account);
        }
        view = new ModelAndView("redirect:../../../detailsPage/" + favoriteFabric.getId());
        return view;
    }
}

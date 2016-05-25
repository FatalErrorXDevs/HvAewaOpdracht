package nl.hva.controller.admin;

import java.io.IOException;
import nl.hva.controller.SuperController;
import nl.hva.model.Page;
import nl.hva.service.fabric.FabricService;
import nl.hva.service.ProjectService;
import nl.hva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller handles everything after /Admin/ and is protected by Spring security so that only
 * ROLE_ADMIN users can view these pages.
 *
 * @version 1.0
 * @since 06-10-2015
 * @author Bert Kooij
 */
@Controller
@RequestMapping("/Admin/")
public class AdminController extends SuperController {

    /**
     * URL of List of Fabrics
     */
    private static final String URL_LIST = "/";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../menus/Admin.jsp");
    
    /**
     * Main viewname.
     */
    private static final String MODEL_ADMIN = "admin/Admin";

    @Autowired
    private UserService userService;

    @Autowired
    private FabricService fabricService;

    @Autowired
    private ProjectService projectService;

    /**
     * Handler for fabric list.
     *
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_LIST)
    public ModelAndView list() throws IOException {
        ModelAndView view = new ModelAndView(MODEL_ADMIN);
        prepareModelAndView(view, PAGE);
        
        /** Add basic statistics to the view. */
        view.addObject("numAccount", userService.count());
        view.addObject("numFabric", fabricService.count());
        view.addObject("numProject", projectService.count());
        return view;
    }

}

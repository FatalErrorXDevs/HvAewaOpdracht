package nl.hva.controller.admin;

import java.io.IOException;
import nl.hva.controller.SuperController;
import nl.hva.model.Page;
import nl.hva.model.fabric.Tab;
import nl.hva.service.fabric.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This coontroller is responsible for creating, updating and viewing tabs and is protected by
 * Spring security so that only ROLE_ADMIN users can view these pages.
 *
 * @version 1.0
 * @since 07-01-2016
 * @author Bert Kooij, Yassine el Abdellaoui, Tom Prins, Xander Nieveld, Floris van Lent
 */
@Controller
@RequestMapping("/Admin/Tabs/")
public class TabController extends SuperController {

    /**
     * Main URL of controller
     */
    private static final String URL = "/Admin/Tabs/";

    private static final String URL_LIST = "/";

    private static final String URL_ADD = "/Add/";

    private static final String URL_SAVE = "/Save/";

    private static final String URL_EDIT = "/{id}/Edit/";

    private static final String URL_DELETE = "/{id}/Delete/";

    private static final String MODEL_FORM = "/admin/tab/Add";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../../menus/Admin.jsp");

    @Autowired
    private TabService tabService;

    @RequestMapping(value = URL_LIST)
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("admin/tab/list");
        prepareModelAndView(view, PAGE);
        view.addObject("list", tabService.getAll());
        return view;
    }

    @RequestMapping(value = URL_ADD)
    public ModelAndView add(Model model) {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        Tab tab = new Tab();
        view.addObject("Tab", tab);
        return view;
    }

    @RequestMapping(value = URL_SAVE)
    public ModelAndView add(@ModelAttribute("Tab") Tab tab,
            BindingResult result, Model model) {
        ModelAndView view;

        /**
         * When validation isn't successfull.
         */
        if (result.hasErrors()) {

            /**
             * Show the form, add failure message and populate view with model.
             */
            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);

            view.addObject("Tab", tab);
            view.addObject("css", "error");
            view.addObject("msg", "ERROR");
        } else {
            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);
            view = list();
            if (tab.isNew()) {
                tab.setCreatedBy(getUser());
                tab.setLastModifiedBy(getUser());
                tabService.save(tab);
                addSucces(getMessage("tabs.add.success", tab.getName()), view);
            } else {
                tab.setLastModifiedBy(getUser());
                tabService.update(tab);
                addSucces(getMessage("tabs.edit.success", tab.getName()), view);
            }
        }
        return view;
    }

    @RequestMapping(value = URL_EDIT)
    public ModelAndView edit(@PathVariable("id") int id, Model model) {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        Tab tab = tabService.getById(id);
        view.addObject("Tab", tab);
        return view;
    }

    @RequestMapping(value = URL_DELETE, method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        Tab tab = tabService.getById(id);
        tabService.delete(tab);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg",
                getMessage("tabs.delete.success", tab.getName()));

        return new ModelAndView("redirect:" + URL);
    }
}

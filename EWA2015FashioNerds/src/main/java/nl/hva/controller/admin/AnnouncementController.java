package nl.hva.controller.admin;

import java.io.IOException;
import nl.hva.controller.SuperController;
import nl.hva.model.Announcement;
import nl.hva.model.Page;
import nl.hva.service.AnnouncementService;
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
 * @version 1.0
 * @since 03-01-2016
 * @author Floris van Lent, Bert Kooij
 */
@Controller
@RequestMapping("/Admin/Announcements/")
public class AnnouncementController extends SuperController {

    private static final String URL = "/Admin/Announcements/";

    private static final String URL_LIST = "/";

    private static final String URL_ADD = "/Add/";

    private static final String URL_SAVE = "/Save/";

    private static final String URL_EDIT = "/{id}/Edit/";

    private static final String URL_DELETE = "/{id}/Delete/";

    private static final String MODEL_VIEW = "/admin/announcements/List";

    private static final String MODEL_ADD = "/admin/announcements/Add";

    private static final Page PAGE = new Page("../../menus/Admin.jsp");

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value = URL_LIST)
    public ModelAndView list() throws IOException {

        ModelAndView view = new ModelAndView(MODEL_VIEW);
        prepareModelAndView(view, PAGE);
        view.addObject("list", announcementService.getAll());

        return view;
    }

    @RequestMapping(value = URL_ADD)
    public ModelAndView add(Model model) throws IOException {

        ModelAndView view = new ModelAndView(MODEL_ADD);
        prepareModelAndView(view, PAGE);
        Announcement announcement = new Announcement();
        view.addObject("Announcement", announcement);

        return view;
    }

    @RequestMapping(value = URL_SAVE)
    public ModelAndView add(@ModelAttribute("Announcement") Announcement announcement,
        BindingResult result, Model model) throws IOException, Exception {
        ModelAndView view;

        /**
         * When validation isn't successful.
         */
        if (result.hasErrors()) {

            /**
             * Show the form, add failure message and populate view with model.
             */
            view = new ModelAndView(MODEL_ADD);
            prepareModelAndView(view, PAGE);

            view.addObject("Announcement", announcement);
            view.addObject("css", "error");
            view.addObject("msg", messageSource.getMessage("global.error", null,
                getUser().getLanguage()));
        } /**
         * When validation is successful.
         */
        else {
            view = new ModelAndView(MODEL_ADD);
            prepareModelAndView(view, PAGE);
            System.out.println(announcement.getAnnouncementID());

            if (announcement.isNew()) {
                announcement.setCreatedBy(getUser());
                announcement.setLastModifiedBy(getUser());
                announcementService.save(announcement);

                /**
                 * Add success message for new announcement.
                 */
                view = list();
                view.addObject("css", "success");
                view.addObject("msg",
                    getMessage("announcements.add.success", announcement.getTitle()));

            } else {
                announcement.setLastModifiedBy(getUser());
                announcementService.update(announcement);

                /**
                 * Add success message for existing announcement.
                 */
                view = list();
                view.addObject("css", "success");
                view.addObject("msg",
                    getMessage("announcements.edit.success", announcement.getTitle()));
            }
        }

        return view;
    }

    @RequestMapping(value = URL_EDIT)
    public ModelAndView edit(@PathVariable("id") int id, Model model) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_ADD);
        prepareModelAndView(view, PAGE);
        Announcement announcement = announcementService.getById(id);
        view.addObject("Announcement", announcement);

        return view;
    }

    @RequestMapping(value = URL_DELETE, method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id,
        final RedirectAttributes redirectAttributes) throws IOException {

        Announcement announcement = announcementService.getById(id);
        announcementService.delete(announcement);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg",
            getMessage("announcements.delete.success", announcement.getTitle()));

        return new ModelAndView("redirect:" + URL);
    }
}

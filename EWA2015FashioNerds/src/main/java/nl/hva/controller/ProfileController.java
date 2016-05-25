package nl.hva.controller;

import java.io.IOException;
import nl.hva.model.Page;
import nl.hva.model.Profile;
import nl.hva.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version 1.0
 * @since 06-01-2016
 * @author Floris van Lent
 */
@Controller
@RequestMapping("/Profile/")
public class ProfileController extends SuperController {

    @Autowired
    private ProfileService profileService;

    private static final String URL_VIEW = "/View";

    private static final String URL_EDIT = "/Edit";

    private static final String MODEL_VIEW = "profile/View";

    private static final String MODEL_EDIT = "profile/Edit";

    private static final Page PAGE = new Page("../menus/Profile.jsp");

    /**
     * This shows the profile of the currently logged in user.
     *
     * @return the ModelAndView that displays a profile
     * @throws IOException
     */
    @RequestMapping(value = URL_VIEW)
    public ModelAndView viewProfile() throws IOException {

        ModelAndView view = new ModelAndView(MODEL_VIEW);
        prepareModelAndView(view, PAGE);
        view.addObject("profile", getUser().getProfile());

        return view;
    }

    /**
     * This method makes it possible to view other user's profiles.
     *
     * @param id the ProfileID to show
     * @return the ModelAndView that displays a profile
     * @throws IOException
     */
    @RequestMapping(value = "/View/{id}/")
    public ModelAndView viewProfile(@PathVariable("id") int id) throws IOException {

        ModelAndView view = new ModelAndView(MODEL_VIEW);
        prepareModelAndView(view, PAGE);
        view.addObject("profile", profileService.getProfileByProfileID(id));

        return view;
    }

    /**
     * Shows the screen for editing profiles and populates the fields with data from the currently
     * logged in user.
     *
     * @return the ModelAndView for editing profiles with populated fields
     * @throws IOException 
     */
    @RequestMapping(value = URL_EDIT)
    public ModelAndView showEditProfile() throws IOException {

        ModelAndView editView = new ModelAndView(MODEL_EDIT);
        prepareModelAndView(editView, PAGE);
        editView.addObject("Profile", getUser().getProfile());

        return editView;
    }
    
    /**
     * This method actually updates the Profile based on the edited fields in Edit.jsp
     *
     * @param profile the edited profile to be saved in the database
     * @return a Model and View based on viewProfile() with success-message
     * @throws IOException 
     */
    @RequestMapping(value = URL_EDIT, method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute("editProfile") Profile profile)
        throws IOException {

        ModelAndView editMethod = new ModelAndView(MODEL_EDIT);
        prepareModelAndView(editMethod, PAGE);
        profile.setAccount(getUser());
        profileService.updateProfile(profile);
        refreshUser();
        editMethod.addObject("Profile", profile);

        ModelAndView viewProfile = viewProfile();
        viewProfile.addObject("css", "success");
        viewProfile.addObject("msg", getMessage("profile.edit.success", null));

        return viewProfile;
    }
}

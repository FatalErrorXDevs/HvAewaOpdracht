package nl.hva.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.hva.model.User;
import nl.hva.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import nl.hva.service.AnnouncementService;
import nl.hva.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This controller handles the main pages of the application.
 *
 * @version 1.0
 * @since 01-10-2015
 * @author Bert Kooij, Tom Prins
 */
@Controller
public class MainController extends SuperController {
    
    /**
     * Model location for Login Form
     */
    private static final String MODEL_LOGIN = "login";

    /**
     * Model location for Password Reset Form
     */
    private static final String MODEL_PASSWORD_RESET = "passwordReset";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../menus/FabricSearch.jsp");

    /**
     * Homepage of application (Redirect)
     */
    public static final String HOMEPAGE = "Fabrics/";

    @Autowired
    AnnouncementService announcementService;
    
    @Autowired
    UserService userService;

//    @Autowired
//  private AccountService userDetailsService; 
    /**
     * Handler for home.
     *
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        return new ModelAndView("redirect:" + HOMEPAGE);
    }

    /**
     * Handler for signing out.
     *
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/SignOut")
    public ModelAndView signOut(HttpServletRequest request, HttpServletResponse response,
            final RedirectAttributes redirectAttributes) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Successfully signed out.");

        return new ModelAndView("redirect:/login");
    }

    /**
     * Handler for saving password.
     *
     * @param password
     * @param password2 Password confirmation.
     * @param redirectAttributes
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/SavePassword", method = RequestMethod.POST)
    public String updatePassword(@RequestParam("password") String password,
            @RequestParam("password2") String password2,
            final RedirectAttributes redirectAttributes) throws IOException {

        User user = getUser();

        if (password.equals(password2)) {

            user.setPassword(password);
            user.setRequirePasswordChange(false);

            try {
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
                userService.saveOrUpdate(user);

                redirectAttributes.addFlashAttribute("css", "success");
                redirectAttributes.addFlashAttribute("msg", getMessage("accounts.password.reset.success"));
            } catch (Exception ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                redirectAttributes.addFlashAttribute("css", "error");
                redirectAttributes.addFlashAttribute("msg", getMessage("global.error.database"));
            }

        } else {
            redirectAttributes.addFlashAttribute("css", "error");
            redirectAttributes.addFlashAttribute("msg", getMessage("accounts.password.reset.error.match"));
        }

        return "redirect:/" + HOMEPAGE;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, HttpServletResponse response) {
        refreshUser();
        ModelAndView model = new ModelAndView(MODEL_LOGIN);
        prepareModelAndView(model, PAGE);
        if (error != null) {
            model.addObject("msg", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
            model.addObject("css", "error");
        }

        if (logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            model.addObject("msg", "You've been logged out successfully. ");

        }
//        model.setViewName(HOMEPAGE);

        /**
         * Import a list of all announcements so they can be displayed HELLUP DIT MAAKT ALLES KAPOT
         */
//        AnnouncementService announcements = new AnnouncementService();
        model.addObject("list", announcementService.getAll());
        return model;

    }

    /**
     * Handler for reseting password form.
     *
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/passwordReset")
    public ModelAndView passwordChange() throws IOException {

        ModelAndView view = new ModelAndView(MODEL_PASSWORD_RESET);

        prepareModelAndView(view, PAGE);

        return view;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

}

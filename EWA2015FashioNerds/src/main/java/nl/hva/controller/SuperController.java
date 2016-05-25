package nl.hva.controller;

import java.util.Locale;
import javax.servlet.http.HttpSession;
import nl.hva.model.Page;
import nl.hva.model.User;
import nl.hva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Super controller has methods which all controllers use.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Bert Kooij
 */
@Component
public abstract class SuperController {

    /**
     * Default language of system.
     */
    private static final Locale DEFAULT_LANGUAGE = Locale.ENGLISH;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    private UserService userService;

    private User user;

    /**
     * Prepares a ModelAndView. This method adds object to the modelAndView.
     *
     * @param view ModelAndView that needs to be prepared.
     * @param page Instance of Page that needs to be added.
     * @return Prepared ModelAndView
     */
    protected ModelAndView prepareModelAndView(ModelAndView view, Page page) {
        view.addObject("myAccount", getUser());
        /**
         * Set language of account.
         */
        if (getUser() != null) {
            prepareLanguage(getUser().getLanguage());
        } else {
            prepareLanguage(DEFAULT_LANGUAGE);
        }

        view.addObject("Page", page);
        return view;
    }

    /**
     * Save language in session.
     */
    private void prepareLanguage(Locale locale) {
        getHttpSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
    }

    protected String getMessage(String code) {
        return this.getMessage(code, null);
    }

    protected String getMessage(String code, Object arg) {
        return getMessage(code, new Object[]{arg});
    }

    protected String getMessage(String code, Object[] arg) {
        Locale l = (getUser() != null) ? getUser().getLanguage() : DEFAULT_LANGUAGE;
        return messageSource.getMessage(code, arg, l);
    }

    public void addSucces(String message, ModelAndView view) {
        view.addObject("css", "success");
        view.addObject("msg", message);
    }

    public void addError(String message, ModelAndView view) {
        view.addObject("css", "error");
        view.addObject("msg", message);
    }

    /**
     * Get user.
     *
     * @return
     */
    public User getUser() {
        if (user != null) {
            return user;
        }
        return refreshUser();
    }

    /**
     * Reload the user.
     *
     * @return
     */
    public User refreshUser() {
        org.springframework.security.core.Authentication secure = SecurityContextHolder.getContext().getAuthentication();
        if (secure != null
                && secure.getName() != null) {
            user = userService.findAccount(secure.getName());
            return user;
        }
        return null;
    }

    
    /**
     * Helper for this class. Get the HttpSession.
     *
     * @return HttpSession
     */
    protected static HttpSession getHttpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = attr.getRequest().getSession();

        return httpSession;
    }
}

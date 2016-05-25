package nl.hva.controller.admin;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import nl.hva.controller.SuperController;
import nl.hva.model.User;
import nl.hva.model.Page;
import nl.hva.model.Profile;
import nl.hva.model.Project;
import nl.hva.model.UserRole;
import nl.hva.service.ProfileService;
import nl.hva.service.ProjectService;
import nl.hva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller handles everything after /Accounts/ and is protected by Spring security so that
 * only ROLE_ADMIN users can view these pages.
 *
 * @version 1.0
 * @since 06-10-2015
 * @author Bert Kooij, Tom Prins, Yassine El Abdellaoui
 */
@Controller
@RequestMapping("/Admin/Accounts/")
public class AccountController extends SuperController {

    /**
     * Main URL of controller
     */
    private static final String URL = "/Admin/Accounts/";

    /**
     * URL of List of Accounts
     */
    private static final String URL_LIST = "/";

    /**
     * URL of search List of Accounts
     */
    private static final String URL_SEARCH = "/Search";

    /**
     * URL for saving Account
     */
    private static final String URL_SAVE = "/Save";

    /**
     * URL for adding Account
     */
    private static final String URL_ADD = "/Add";

    /**
     * URL for adding Account
     */
    private static final String URL_ADD_ACCOUNT_TO_PROJECT = "/{projectId}/addAccountToProject";

    /**
     * Model location for List Account
     */
    private static final String MODEL_LIST = "account/Accounts";

    /**
     * Model location for after Saving
     */
    private static final String MODEL_SAVE = "account/Accounts";

    /**
     * Model location for after Saving
     */
    private static final String URL_SAVE_ACCOUNT_TO_PROJECT = "/{projectId}/{accountId}/SaveToProject";

    /**
     * Model location for Account Form
     */
    private static final String MODEL_FORM = "account/Add";

    private static final String MODEL_PASS = "account/WijzigPassword";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../menus/Admin.jsp");

//    @Autowired
//    private AccountService accountService;
//    
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    /**
     * Handler for account list.
     *
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_LIST)
    public ModelAndView list() throws IOException {
        ModelAndView view;
        view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        view.addObject("list", userService.getAllAccounts());

        return view;
    }

    @RequestMapping(value = URL_ADD_ACCOUNT_TO_PROJECT)
    public ModelAndView addAccountToProject(@PathVariable int projectId) throws IOException {
        ModelAndView view;
        view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        view.addObject("list", userService.getAllAccounts());
        view.addObject("projectId", projectId);

        return view;
    }

    @RequestMapping(value = URL_SAVE_ACCOUNT_TO_PROJECT)
    public ModelAndView saveAccountToProject(@PathVariable int projectId, @PathVariable int accountId) throws IOException, Exception {
        ModelAndView view;
        view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        Project project = projectService.getProjectById(projectId);
        User account = userService.findAccount(accountId);
        List<Project> projectList = account.getProjects();
        projectList.add(project);
        account.setProjects(projectList);
        userService.saveOrUpdate(account);
        view.addObject("list", userService.getAllAccounts());
        view.addObject("projectId", projectId);

        return view;
    }

    /**
     * Handler for searching in account list.
     *
     * @param search
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_SEARCH, method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("s") String search) throws IOException {
        ModelAndView view;
        view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        view.addObject("list", userService.searchAll(search));
        view.addObject("search", search);

        return view;
    }

    /**
     * Handler for saving an account.
     *
     * @param account Model Account
     * @param result results of validation
     * @param model
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_SAVE, method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("User") @Validated User account,
            BindingResult result, Model model) throws IOException {

        ModelAndView view;

        if (result.hasErrors()) {

            /**
             * Show the form, add failure message and populate view with model.
             */
            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);
            prepareModel(model);

            view.addObject("Account", account);

            view.addObject("css", "error");
            view.addObject("msg", getMessage("accounts.save.fail.details"));
        } else {

            /**
             * Show list with accounts when validation is successfull.
             */
            view = new ModelAndView(MODEL_SAVE);
            prepareModelAndView(view, PAGE);

            /**
             * Update the account in database.
             */
            if (account.freshAccount()) {
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                account.setPassword(encoder.encode(account.getPassword()));
                account.setEnabled(true);

                userService.saveOrUpdate(account);

                Profile newProfile = new Profile(0, account);
                profileService.saveProfile(newProfile);
            } else {

                userService.saveOrUpdate(account);

            }

            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);
            view.addObject("User", account);
            prepareModel(model);
            /**
             * Add success message.
             */
            view.addObject("css", "success");
            view.addObject("msg", getMessage("accounts.save.success", account.getUsername()));

        }
        return view;
    }

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    public ModelAndView saveOrUpdatePassword(
            @RequestParam(value = "id", required = false) int id,
            @RequestParam(value = "password", required = false) String password, Model model) {
        ModelAndView view;
        try {

            PasswordEncoder encoder = new BCryptPasswordEncoder();

            User user = userService.findAccount(id);
            user.setPassword(encoder.encode(password));

            userService.saveOrUpdate(user);

            view = new ModelAndView(MODEL_LIST);
            prepareModelAndView(view, PAGE);
            view.addObject("list", userService.getAllAccounts());
            /**
             * Add success message.
             */
            view.addObject("css", "success");
            view.addObject("msg", getMessage("accounts.save.success", user.getUsername()));

            return view;
        } catch (Exception e) {

            view = new ModelAndView(MODEL_LIST);
            prepareModelAndView(view, PAGE);
            view.addObject("list", userService.getAllAccounts());
            prepareModel(model);

            view.addObject("css", "error");
            view.addObject("msg", getMessage("accounts.save.fail.details"));

            return view;
        }

    }

    /**
     * Handler for adding an Account. Populates view with empty Account.
     *
     * @param model
     * @return Model and View
     * @throws java.io.IOException
     */
    @RequestMapping(value = URL_ADD)
    public ModelAndView add(Model model) throws IOException {
        ModelAndView view;
        view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        prepareModel(model);
        User account = new User();
        view.addObject("User", account);

        return view;
    }

    /**
     * Handler for editing an Account.
     *
     * @param id ID of account that needs to be edit.
     * @param model
     * @return Model and View
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/Edit/{id}")
    public ModelAndView editUser(@PathVariable("id") int id, Model model) throws IOException {
        ModelAndView view;
        view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        prepareModel(model);

        /**
         * Find Account and populate view with the account.
         */
        User account = userService.findAccount(id);
        view.addObject("User", account);

        return view;
    }

    @RequestMapping(value = "/Editpass/{id}")
    public ModelAndView editPassword(@PathVariable("id") int id, Model model) throws IOException {
        ModelAndView view;
        view = new ModelAndView(MODEL_PASS);
        prepareModelAndView(view, PAGE);
        prepareModel(model);

        /**
         * Find Account and populate view with the account.
         */
        User account = userService.findAccount(id);
        view.addObject("User", account);

        return view;
    }

    /**
     * Handler for deleteing an Account.
     *
     * @param id ID of account that needs to be deleted.
     * @param redirectAttributes
     * @return Model and View
     * @throws java.io.IOException
     */
    /**
     * Add default data to model.
     *
     * @param model
     * @todo roles from database.
     */
    private void prepareModel(Model model) {
        model.addAttribute("rightList", userService.getAllRoles());

        Map<Locale, String> languages = new LinkedHashMap<>();
        languages.put(Locale.ENGLISH, getMessage("global.language.english"));
        languages.put(new Locale("nl"), getMessage("global.language.dutch"));
        model.addAttribute("languageList", languages);
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(UserRole.class, "userRole", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                UserRole data = userService.getUserRoleById(Integer.valueOf(text));
                setValue(data);
            }

        });
    }

}

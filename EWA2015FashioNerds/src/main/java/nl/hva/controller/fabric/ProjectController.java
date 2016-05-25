package nl.hva.controller.fabric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nl.hva.controller.SuperController;
import nl.hva.model.Account;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.Page;
import nl.hva.model.Project;
import nl.hva.model.User;
import nl.hva.service.AccountService;
import nl.hva.service.fabric.CategoryService;
import nl.hva.service.fabric.FabricService;
import nl.hva.service.ProjectService;
import nl.hva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller handles everything after /Fabrics/.
 *
 * @version 1.0
 * @since 06-10-2015
 * @author Bert Kooij, Yassine el Abdellaoui, Tom Prins
 */
@Controller
@RequestMapping("/Projects/")
public class ProjectController extends SuperController {

    /**
     * Main URL of controller
     */
    private static final String URL = "/Projects/";

    /**
     * URL of List of Project
     */
    private static final String URL_LIST = "/";

    /**
     * URL of List of Project
     */
    private static final String URL_TO_PROJECT_DETAILS = "/{fabricId}/list";

    /**
     * URL of Search List of Project
     */
    private static final String URL_SEARCH = "/Search";

    /**
     * URL for saving Project
     */
    private static final String URL_SAVE = "/Save";

    /**
     * URL for adding Project
     */
    private static final String URL_ADD = "/Add";

    /**
     *
     */
    private static final String URL_PUBLISH = "/{fabricId}/{projectId}/publish";

    /**
     *
     */
    private static final String URL_ADD_TO_PROJECT = "/{projectId}/{fabricId}/addToProject";

    /**
     *
     */
    private static final String URL_ADD_ACCOUNT_TO_PROJECT = "/{accountId}/{projectId}/addAccountToProject";

    /**
     * URL for deleting Fabric
     */
    private static final String URL_DELETE = "/{id}/delete";

    /**
     * URL for deleting from Projects
     */
    private static final String URL_DELETE_FROM_PROJECTS = "/{projectId}/{fabricId}/deletefromprojects";

    /**
     * Model location for Listing Fabrics
     */
    private static final String MODEL_LIST = "projects/Projects";

    /**
     * Model location for Listing Fabrics
     */
    private static final String MODEL_LIST_ACCOUNTS = "projects/Overzicht";

    /**
     *
     */
    private static final String URL_OVERZICHT_ACCOUNTS = "/{projectId}/Overzicht";

    /**
     *
     */
    private static final String URL_DELETE_ACCOUNT_FROM_PROJECTS = "/{projectId}/{accountId}/deleteAccountFromProject";

    /**
     *
     */
    private static final String MODEL_DETAILS = "/projects/detailsPage";

    /**
     * Model location for Fabric Form
     */
    private static final String MODEL_FORM = "projects/Add";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../menus/ProjectsMenu.jsp");

    /**
     *
     */
    private static final Page PAGE_FABRIC = new Page("../menus/FabricSearch.jsp");

    @Autowired
    private ProjectService projectService;
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

        view.addObject("list", account.getProjects());

        return view;
    }

    /**
     *
     * @param projectId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = URL_OVERZICHT_ACCOUNTS)
    public ModelAndView listAccounts(@PathVariable int projectId) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST_ACCOUNTS);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        List<User> userList = userService.getAllAccounts();
        List<User> userNewList = new ArrayList<>();
        Project project = projectService.getProjectById(projectId);
        for (int i = 0; i < userList.size(); i++) {
            List<Project> projecList = userList.get(i).getProjects();
            for (int j = 0; j < projecList.size(); j++) {
                if (projecList.get(j).getProjectID() == projectId) {
                    userNewList.add(userList.get(i));
                }
            }
        }
        view.addObject("accountId", project.getCreatedById());
        view.addObject("list", userNewList);
        view.addObject("projectId", projectId);

        return view;
    }

    /**
     *
     * @param fabricId
     * @param projectId
     * @return @throws IOException
     */
    @RequestMapping(value = URL_PUBLISH)
    public ModelAndView publish(@PathVariable int fabricId, @PathVariable int projectId) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_DETAILS);
        prepareModelAndView(view, PAGE);
        Fabric fabric = fabricService.getFabricById(fabricId);
        fabricService.update(fabric);
        Project project = projectService.getProjectById(projectId);
        List<Fabric> projectFabricList = project.getFabrics();
        User account = getUser();
        view.addObject("list", projectFabricList);
        view.addObject("listProject", account.getProjects());
        view.addObject("id", projectId);
        return new ModelAndView("redirect:/Projects/detailsPage/"+project.getProjectID());
    }

    /**
     *
     * @param id
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = URL_DELETE)
    public ModelAndView delete(@PathVariable int id) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        Project projects = projectService.getProjectById(id);
        List<Project> projectList = account.getProjects();
        for (int i = 0; i < projectList.size(); i++) {
            if ((projectList.get(i)).getProjectID() == projects.getProjectID()) {
                projectList.remove(i);
            }

        }
        account.setProjects(projectList);
        userService.saveOrUpdate(account);
        view.addObject("list", account.getProjects());
        view.addObject("id", id);

        return new ModelAndView("redirect:/Projects/detailsPage/"+projects.getProjectID());
    }

    /**
     *
     * @param projectId
     * @param fabricId
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = URL_DELETE_FROM_PROJECTS)
    public ModelAndView deleteFromProjects(@PathVariable int projectId, @PathVariable int fabricId) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_DETAILS);
        prepareModelAndView(view, PAGE);
        Project project = projectService.getProjectById(projectId);
        Fabric fabric = fabricService.getFabricById(fabricId);
        List<Project> projectList = fabric.getProject();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getProjectID() == project.getProjectID()) {
                projectList.remove(i);
            }
        }
        fabric.setProject(projectList);
        fabricService.update(fabric);
        return new ModelAndView("redirect:/Projects/detailsPage/"+project.getProjectID());
    }

    @RequestMapping(value = URL_DELETE_ACCOUNT_FROM_PROJECTS)
    public ModelAndView deleteAccountFromProjects(@PathVariable int projectId, @PathVariable int accountId) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_LIST_ACCOUNTS);
        prepareModelAndView(view, PAGE);
        User account = userService.findAccount(accountId);
        List<Project> projectList = account.getProjects();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getProjectID() == projectId) {
                projectList.remove(i);
            }
        }
        account.setProjects(projectList);
        userService.saveOrUpdate(account);

        view.addObject("projectId", projectId);
        view.addObject("accountId", account.getId());
        return view;
    }

    /**
     *
     * @param projectName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = URL_SEARCH)
    public ModelAndView search(@RequestParam("s") String projectName) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        List<Project> resultProject = projectService.searchProjectByName(account.getId(), projectName);
        view.addObject("list", resultProject);
        view.addObject("search", projectName);

        return view;
    }

    /**
     *
     * @param fabricId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = URL_TO_PROJECT_DETAILS)
    public ModelAndView list1(@PathVariable Integer fabricId) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        List<Integer> fabricIdList = new ArrayList();
        for (int i = 0; i < account.getProjects().size(); i++) {
            fabricIdList.add(fabricId);
        }

        System.out.println(fabricIdList.get(0));
        view.addObject("listId", fabricIdList);
        view.addObject("list", account.getProjects());

        return view;
    }

    /**
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/detailsPage/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable Integer id) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_DETAILS);
        prepareModelAndView(view, PAGE);
        Project project = projectService.getProjectById(id);
        List<Fabric> projectFabricList = project.getFabrics();
        User account = getUser();
        view.addObject("list", projectFabricList);
        view.addObject("listProject", account.getProjects());
        view.addObject("project", project);
        view.addObject("id", id);
        view.addObject("categories", categoryService.getAll());
        return view;

    }

    /**
     *
     * @param project
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = URL_SAVE)
    public ModelAndView add(@ModelAttribute Project project) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        project.setCreatedById(account.getId());
        System.out.println(project.getProjectName() + " " + project.getProjectDescription() + "" + project.getCreatedById());
        projectService.addProject(project);

        List<Project> projects = account.getProjects();

        projects.add(project);
        account.setProjects(projects);
        userService.saveOrUpdate(account);

        return new ModelAndView("redirect:/Projects/detailsPage/"+project.getProjectID());
    }

    /**
     *
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = URL_ADD)
    public ModelAndView add(Model model) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        User account = getUser();
        Fabric fabric = new Fabric();
        view.addObject("Fabric", fabric);

        return view;
    }

    /**
     *
     * @param projectId
     * @param fabricId
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = URL_ADD_TO_PROJECT)
    public ModelAndView addToProjects(@PathVariable int projectId, @PathVariable int fabricId) throws IOException, Exception {
        ModelAndView view = new ModelAndView(MODEL_DETAILS);
        prepareModelAndView(view, PAGE);
        Project project = projectService.getProjectById(projectId);
        Fabric fabric = fabricService.getFabricById(fabricId);
        System.out.println(project.toString());
        List<Fabric> fabricList = project.getFabrics();
        List<Project> projectList = fabric.getProject();
        projectList.add(project);
        fabricList.add(fabric);
        fabric.setProject(projectList);
        project.setFabrics(fabricList);
        fabricService.update(fabric);
        return new ModelAndView("redirect:/Projects/detailsPage/"+project.getProjectID());
    }
}

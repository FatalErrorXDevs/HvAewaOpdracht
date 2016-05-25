package nl.hva.controller.admin;

import java.beans.PropertyEditorSupport;
import javax.servlet.http.HttpServletRequest;
import nl.hva.controller.SuperController;
import nl.hva.input.InputType;
import nl.hva.model.Page;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.CategoryMulti;
import nl.hva.model.fabric.CategorySingle;
import nl.hva.model.fabric.data.DataMulti;
import nl.hva.model.fabric.Tab;
import nl.hva.service.fabric.CategoryService;
import nl.hva.service.fabric.DataService;
import nl.hva.service.fabric.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This coontroller is responsible for creating, updating and viewing categories and is protected
 * by Spring security so that only ROLE_ADMIN users can view these pages.
 *
 * @version 1.0
 * @since 07-01-2016
 * @author Bert Kooij
 */
@Controller
@RequestMapping("/Admin/Categories/")
public class CategoryController extends SuperController {

    /**
     * Main URL of controller
     */
    private static final String URL = "/Admin/Categories/";

    private static final String URL_LIST = "/";

    private static final String URL_ADD = "/Add/{type}";

    private static final String URL_SAVE = "/Save/";

    private static final String URL_EDIT = "/{id}/Edit/";

    private static final String URL_DETAILS = "/{id}/Details/";

    private static final String URL_DELETE = "/{id}/Delete/";

    private static final String MODEL_FORM = "/admin/categories/Add";

    private static final String MODEL_DETAILS = "/admin/categories/Details";

    /**
     * Default object of Page for this controller.
     */
    private static final Page PAGE = new Page("../../menus/Admin.jsp");

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TabService tabService;

    @Autowired
    private DataService dataService;

    /**
     * List of categories.
     *
     * @return
     */
    @RequestMapping(value = URL_LIST)
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("admin/categories/list");
        prepareModelAndView(view, PAGE);
        view.addObject("categoryList", categoryService.getAll());
        view.addObject("InputTypes", InputType.values());
        view.addObject("InputTypesL", InputType.values().length);
        return view;
    }

    /**
     * Open add form for category.
     *
     * @param model
     * @param type Input type id.
     * @see nl.hva.input.InputType
     * @return
     */
    @RequestMapping(value = URL_ADD)
    public ModelAndView add(Model model, @PathVariable("type") int type) {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        Category category;

        /**
         * Check if category must support multiple data items.
         */
        InputType inputType = InputType.parse(type);
        String saveURL = URL + "Save/";
        if (InputType.parse(type).isMultipleItems()) {
            category = new CategoryMulti();
            saveURL += "Multi/";
        } else {
            category = new CategorySingle();
            saveURL += "Single/";
        }
        category.setDataType(inputType);

        view.addObject("Category", category);
        view.addObject("saveURL", saveURL);
        view.addObject("Tabs", tabService.getAll());
        return view;
    }

    /**
     * Save category which is instanceof CategorySingle.
     *
     * @param category
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = URL_SAVE + "Single/")
    public ModelAndView saveSingle(@ModelAttribute("Category") CategorySingle category,
            BindingResult result, Model model) {
        return save(category, result, model);
    }

    @RequestMapping(value = URL_SAVE + "Multi/")
    public ModelAndView saveMulti(@ModelAttribute("Category") CategoryMulti category,
            BindingResult result, Model model) {
        return save(category, result, model);
    }

    public ModelAndView save(Category category, BindingResult result, Model model) {
        ModelAndView view;

        /**
         * When validation isn't successfull.
         */
        if (result.hasErrors()) {
            view = addErrors(result, category);
        } else {
            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);
            view = list();
            if (category.isNew()) {
                category.setCreatedBy(getUser());
                category.setLastModifiedBy(getUser());
                categoryService.save(category);
                addSucces(getMessage("categories.add.success", category.getName()), view);
            } else {
                category.setLastModifiedBy(getUser());
                categoryService.update(category);
                addSucces(getMessage("categories.edit.success", category.getName()), view);
            }
        }
        return view;
    }

    /**
     * Show the form, add failure message and populate view with model.
     *
     * @param result
     * @param category
     * @return
     */
    public ModelAndView addErrors(BindingResult result, Category category) {

        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);

        view.addObject("Category", category);
        view.addObject("css", "error");
        view.addObject("msg", getMessage("global.error", null));

        String saveURL = URL + "Save/";
        if (InputType.parse(category.getDataType().getId()).isMultipleItems()) {
            saveURL += "Multi/";
        } else {
            saveURL += "Single/";
        }
        view.addObject("saveURL", saveURL);

        return view;
    }
    
    @RequestMapping(value = URL_EDIT)
    public ModelAndView edit(@PathVariable("id") int id, Model model) {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        Category category = categoryService.getById(id);
        view.addObject("Category", category);
        view.addObject("Tabs", tabService.getAll());
        return view;
    }

    @RequestMapping(value = URL_DETAILS)
    public ModelAndView details(@PathVariable("id") int id, Model model) {
        ModelAndView view = new ModelAndView(MODEL_DETAILS);
        prepareModelAndView(view, PAGE);
        Category category = categoryService.getById(id);
        view.addObject("Category", category);
        if (category.getDataType().isMultipleItems()) {
            view.addObject("Data", new DataMulti());
        }
        return view;
    }

    /**
     * Add new data elmement.
     * @param id
     * @param data
     * @param result
     * @param model
     * @param redirectAttributes
     * @return 
     */
    @RequestMapping(value = "/{id}/SaveData/", method = RequestMethod.POST)
    public ModelAndView saveData(@PathVariable("id") int id, DataMulti data, BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {

        Category category = categoryService.getById(id);
        data.setCategory(category);
        data.setReviewed(true);

        dataService.saveOrUpdate(data);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Success");

        return new ModelAndView("redirect:/Admin/Categories/" + id + "/Details/");
    }

    /**
     * Toggle the reviewed status and redirects back to the category details page.
     *
     * @param id
     * @param redirectAttributes
     * @return Redirect
     */
    @RequestMapping(value = "/{id}/Toggle/", method = RequestMethod.GET)
    public ModelAndView toggleData(@PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        DataMulti dataMulti = (DataMulti) dataService.getById(id);
        dataMulti.setReviewed(!dataMulti.isReviewed());
        dataService.saveOrUpdate(dataMulti);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Success");

        return new ModelAndView("redirect:/Admin/Categories/" + dataMulti.getCategory().getId() + "/Details/");
    }

    /**
     * Delete a category with categoryID.
     *
     * @param id
     * @param redirectAttributes
     */
    @RequestMapping(value = URL_DELETE, method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        Category category = categoryService.getById(id);
        categoryService.delete(category);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", getMessage("categories.delete.success", category.getName()));

        return new ModelAndView("redirect:" + URL);
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(Tab.class, "tab", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Tab tab = tabService.getById(Integer.parseInt(text));
                setValue(tab);
            }
        });
    }

}

package nl.hva.controller.fabric;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.Page;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.CategoryMulti;
import nl.hva.model.fabric.CategorySingle;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.data.DataMulti;
import nl.hva.model.fabric.data.DataSingle;
import nl.hva.model.fabric.data.TempData;
import nl.hva.model.filter.FabricFilter;
import nl.hva.model.filter.FilterModel;
import nl.hva.service.fabric.CategoryService;
import nl.hva.service.fabric.DataService;
import nl.hva.service.fabric.FabricService;
import nl.hva.service.ProjectService;
import nl.hva.service.fabric.TabService;
import nl.hva.validator.FabricValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This controller handles everything after /Fabrics/.
 *
 * @version 1.0
 * @since 06-10-2015
 * @author Bert Kooij, Yassine el Abdellaoui, Tom Prins, Xander Nieveld
 */
@Controller
@RequestMapping("/Fabrics/")
@SessionAttributes({"FabricFilterModel"})
public class FabricController extends FabricFilterImpl {

    /**
     * Model location for Listing Fabrics
     */
    private static final String MODEL_LIST = "fabric/Fabrics";

    /**
     * Model location for details
     */
    private static final String MODEL_DETAILS = "/fabric/detailsPage";

    /**
     * Model location for Fabric Form
     */
    private static final String MODEL_FORM = "fabric/Add";

    /**
     * Default object of Page for this controller.
     */
    private transient static final Page PAGE = new Page("../menus/FabricSearch.jsp");

    @Autowired
    private transient FabricService fabricService;

    @Autowired
    private transient ProjectService projectService;

    @Autowired
    private transient FabricValidator validator;

    @Autowired
    private transient DataService dataService;

    @Autowired
    private TabService tabService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Handler for fabric list.
     *
     * @param filter
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/")
    public ModelAndView list(@ModelAttribute("FabricFilterModel") FabricFilter filter) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        view.addObject("filter", filter);
        return view;
    }

    /**
     *
     * @param filter
     * @param projectId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/list/{projectId}")
    public ModelAndView list1(@ModelAttribute("FabricFilterModel") FabricFilter filter, @PathVariable int projectId) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST);

        prepareModelAndView(view, PAGE);
        view.addObject("filter", filter);
        List<Integer> ids = new ArrayList();
        ids.add(projectId);

        System.out.println(projectId);
        view.addObject("list", ids);
        return view;
    }

    /**
     *
     * @return
     */
    @ModelAttribute("FabricFilterModel")
    public FilterModel refreshFilters() {
        FabricFilter filterModel = new FabricFilter();
        filterModel.setCategoryService(categoryService);
        filterModel.setTabService(tabService);
        filterModel.setCategories((ArrayList<Category>) categoryService.getFilters());
        filterModel.build();
        return filterModel;
    }

    @RequestMapping(value = "/SearchList", method = RequestMethod.POST)
    public ModelAndView responseList(@ModelAttribute("FabricFilterModel") FabricFilter filter) throws IOException {

        ModelAndView view = new ModelAndView("fabric/List");
        prepareModelAndView(view, PAGE);
        view.addObject("list", fabricService.searchMultipleFabrics(filter));
        view.addObject("filter", filter);
        view.addObject("categories", categoryService.getAll());

        return view;
    }

    @RequestMapping(value = "/SearchList", method = RequestMethod.GET)
    public ModelAndView responseListGet(@ModelAttribute("FabricFilterModel") FabricFilter filter) throws IOException {
        return responseList(filter);
    }

    /**
     * Handler for searching fabric list.
     *
     * @param FabricName search for
     * @return ModelAndView
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/Search")
    public ModelAndView search(@RequestParam("s") String FabricName) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_LIST);
        prepareModelAndView(view, PAGE);
        view.addObject("list", fabricService.getFabricsByName(FabricName));
        view.addObject("search", FabricName);

        return view;
    }

    @RequestMapping(value = "/Save")
    public ModelAndView add(@Valid @ModelAttribute("FabricModel") Fabric fabric,
            BindingResult result, RedirectAttributes redir) throws IOException, Exception {
        ModelAndView view;

        validator.validate(fabric, result);

        /**
         * When validation isn't successfull.
         */
        if (result.hasErrors()) {

            /**
             * Show the form, add failure message and populate view with model.
             */
            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);

            view.addObject("Fabric", fabric);

            view.addObject("css", "error");
            view.addObject("msg", "ERROR");
        } else {
            view = new ModelAndView(MODEL_FORM);
            prepareModelAndView(view, PAGE);
            view.addObject("Fabric", fabric);

            try {
                if (fabric.isNew()) {
                    fabric.setCreatedBy(getUser());
                    fabric.setLastModifiedBy(getUser());
                    fabric.addWorkedOn(getUser());
                    fabricService.save(fabric);
                } else {
                    Fabric old = fabricService.getFabricById(fabric.getId());
                    fabric.setWorkedOn(old.getWorkedOn());
                    fabric.setCreatedBy(old.getCreatedBy());
                    fabric.setLastModifiedBy(getUser());
                    fabric.addWorkedOn(getUser());
                    fabricService.update(fabric);
                }

                /**
                 * Add success message.
                 */
                redir.addFlashAttribute("css", "success");
                redir.addFlashAttribute("msg", "Succesvol");

                return new ModelAndView("redirect:/Fabrics/" + fabric.getId() + "/edit/");
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }

        view.addObject("Categories", categoryService.getAll());
        view.addObject("data", dataService.getAll());
        view.addObject("Tabs", tabService.getAll());

        return view;
    }
    

    

//    **
//     * Handler for adding a Fabric.
//     *
//     * @return ModelAndView
//     * @throws java.io.IOException
//     */
    @RequestMapping(value = "/Add")
    public ModelAndView add(Model model) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        Fabric fabric = new Fabric();
        fabric.setCreatedBy(getUser());
        fabric.setLastModifiedBy(getUser());
        System.out.println("ADD");

        for (CategorySingle categorySingle : categoryService.getAllCategoriesSingle()) {
            TempData tempData = new TempData(categorySingle.getId());
            fabric.addTempData(tempData);
        }
        
        view.addObject("FabricModel", fabric);
        view.addObject("Categories", categoryService.getAll());
        view.addObject("data", dataService.getAll());
        view.addObject("Tabs", tabService.getAll());
        view.addObject("dataModel", new DataMulti());

        return view;
    }

    @RequestMapping(value = "/{id}/edit/")
    public ModelAndView edit(@PathVariable("id") int id, Model model) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_FORM);
        prepareModelAndView(view, PAGE);
        Fabric fabric = fabricService.getFabricById(id);
        for (CategorySingle categorySingle : categoryService.getAllCategoriesSingle()) {
            TempData tempData = new TempData(categorySingle.getId());
            DataSingle dataSingle = (DataSingle) fabric.getDataForCategoryId(categorySingle.getId());

            if (dataSingle != null) {
                tempData.setDataId(dataSingle.getId());
                tempData.setValue(dataSingle.getValue());
            }
            fabric.addTempData(tempData);
        }

        view.addObject("FabricModel", fabric);
        view.addObject("Categories", categoryService.getAll());
        view.addObject("data", dataService.getAll());
        view.addObject("Tabs", tabService.getAll());
        view.addObject("dataModel", new DataMulti());

        return view;
    }

    @Override
    protected ModelAndView prepareModelAndView(ModelAndView view, Page page
    ) {
        view = super.prepareModelAndView(view, page);

        view.addObject("filterUrl", "SearchList");

        return view;
    }

    @RequestMapping(value = "/detailsPage/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(@PathVariable Integer id) throws IOException {
        ModelAndView view = new ModelAndView(MODEL_DETAILS);
        prepareModelAndView(view, PAGE);
        Fabric fabric = fabricService.getFabricById(id);
        view.addObject("fabric", fabric);
        view.addObject("Tabs", tabService.getAll());
        view.addObject("categories", categoryService.getAll());

        return view;
    }
    
    @RequestMapping(value = "/AddData/")
    public ModelAndView addData(@RequestParam("catId") int Id, @RequestParam("tabId") int tabId, @RequestParam("value") String value) throws IOException {
        
        DataMulti data = new DataMulti();
        CategoryMulti category = (CategoryMulti) categoryService.getById(Id);
        ModelAndView view;
        switch(category.getDataType())
        {
            case SELECT: view = new ModelAndView("input/extraSelect");
                break;
            case RADIO: view = new ModelAndView("input/extraRadio");
                break;
            default: view = new ModelAndView("input/extraCheck");
                break;
        }
        
        if (category.isUserInput()) {
            data.setCategory(categoryService.getById(Id));
            data.setValue(value);
            dataService.saveOrUpdate(data);
            view.addObject("extra", data);
        } else {
            view.addObject("extra", "Error");
        }
        view.addObject("tab", tabId);
        return view;
    }

    /**
     *
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ResetFilter", method = RequestMethod.GET)
    public ModelAndView resetFilter(Model model) throws IOException {

        model.addAttribute("FabricFilterModel", refreshFilters());
        ModelAndView view = new ModelAndView("redirect:/Fabrics/");

        return view;
    }

    /**
     * Bind validator.
     *
     * @param binder
     */
    protected void initBinder(WebDataBinder binder) {
        System.out.println("Bind validator");
        binder.setValidator(validator);
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(Data.class, "data", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                    return;
                }
                Data data = dataService.getById(Integer.parseInt(text));
                setValue(data);
            }

        });
    }
}

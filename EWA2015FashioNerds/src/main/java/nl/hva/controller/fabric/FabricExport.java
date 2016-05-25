package nl.hva.controller.fabric;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.hva.controller.SuperController;
import nl.hva.pdf.PDFView;
import nl.hva.service.fabric.FabricService;
import nl.hva.service.fabric.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * This controller handles everything after /Fabrics/Export/ and is responsible for exporting a Fabric.
 *
 * @version 1.0
 * @since 06-10-2015
 * @author Xander Nieveld
 */
@Controller
@RequestMapping("/Fabrics/Export/")
@SessionAttributes({"FabricFilterModel"})
public class FabricExport extends SuperController {

    @Autowired
    private transient FabricService fabricService;

    @Autowired
    private TabService tabService;

    
    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public PDFView exportPDF(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("fabric", fabricService.getFabricById(id));
        model.addAttribute("Tabs", tabService.getAll());

        PDFView pdf = new PDFView();
        return pdf;
    }
}

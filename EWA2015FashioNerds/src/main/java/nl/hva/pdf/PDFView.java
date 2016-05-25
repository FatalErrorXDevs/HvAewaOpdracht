package nl.hva.pdf;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import nl.hva.model.fabric.Fabric;
import nl.hva.model.fabric.Category;
import nl.hva.model.fabric.data.Data;
import nl.hva.model.fabric.Tab;

/**
 *
 * @author Xander, Bert
 */
public class PDFView extends AbstractITextPdfView {

    public static int MAX_ITEMS_PER_PAGE = 15;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Fabric fabric = (Fabric) model.get("fabric");

        ArrayList<Tab> tabs = (ArrayList<Tab>) model.get("Tabs");

        Paragraph header = new Paragraph(new Chunk("PDF Export", FontFactory.getFont(FontFactory.HELVETICA, 20)));

        document.add(header);
        for (Tab tab : tabs) {
            Paragraph tabData = new Paragraph(new Chunk(tab.getName() + ": \n\n", FontFactory.getFont(FontFactory.HELVETICA, 18)));
            document.add(tabData);

            PdfPTable myTable = new PdfPTable(2);
            myTable.setWidthPercentage(100.0f);

            PdfPCell cellOne = new PdfPCell(new Paragraph("Hello World"));
            PdfPCell cellTwo = new PdfPCell(new Paragraph("Bottom Left"));

            cellOne.setColspan(2);
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setHorizontalAlignment(Element.ALIGN_LEFT);

            cellTwo.setBorder(Rectangle.NO_BORDER);
            cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);

            int numElements = 0;
            for (Category category : tab.getCategories()) {
                Chunk chunk = new Chunk(category.getName() + ": ", FontFactory.getFont(FontFactory.HELVETICA, 14));
                Paragraph fabricData = new Paragraph(chunk);

                myTable.addCell(fabricData);

                ArrayList<Data> data = (ArrayList<Data>) fabric.getFabricDataByCategory(category);

                fabricData = new Paragraph(new Chunk((data != null && !data.isEmpty()) ? exportData(data) : "", FontFactory.getFont(FontFactory.HELVETICA, 10)));

                myTable.addCell(fabricData);
                numElements++;
                if (numElements == MAX_ITEMS_PER_PAGE) {
                    numElements = 0; 
                    document.add(myTable);
                    document.add(Chunk.NEXTPAGE);
                    myTable = new PdfPTable(2);
                    myTable.setWidthPercentage(100.0f);
                }
            }
            document.add(myTable);
        }

//        Paragraph fabricData = new Paragraph(new Chunk("Name: " + fabric.getFabricName() + "\n" + "Color: " + fabric.getColor() + "\n" + "Weight: " + fabric.getWeight() + "\n" + "State: " + fabric.getState() + "\n" + "Composition: " + fabric.getComposition() + "\n", FontFactory.getFont(FontFactory.HELVETICA, 20)));
        //todo: i10n
        // java.util.ResourceBundle.getBundle("resources/lang/messages_en").getString("fabrics.model.name");
//        document.add(fabricData);
        if (fabric.getFabricName() != null) {
            // null kun je niet toevoegen als title, dus alleen toevoegen mits er resultaat is uit de database
            document.addTitle(fabric.getFabricName());
        }
        document.close();
    }

    private String exportData(ArrayList<Data> dataItems) {
        String ret = "";
        for (Data data : dataItems) {
            ret += data.toExport() + ",";
        }

        return ret.substring(0, ret.length() - 1);
    }
}

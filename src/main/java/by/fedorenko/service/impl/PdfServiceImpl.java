package by.fedorenko.service.impl;

import by.fedorenko.entity.ReportDTO;
import by.fedorenko.entity.UserDTO;
import by.fedorenko.exception.ServiceException;
import by.fedorenko.service.PdfService;
import by.fedorenko.util.JSONParser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class PdfServiceImpl implements PdfService {


    private PdfPTable table;

    private static final Font DOCUMENT_TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private static final String[] HEADERS_NAMES = new String[]{"REPORT TITLE", "REPORT", "LABOR COST"};
    private static final int NUMS_COLUMNS = HEADERS_NAMES.length;
    private static final Font HEADER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font USER_NAME_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font COLS_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14);

    @Override
    public void createPdf(String path) throws ServiceException {

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "reports.pdf"));
            document.open();
            document.add(new Paragraph("Hi, we are Yellow command.\nThis are reports about our workday!\n\n", DOCUMENT_TITLE_FONT));
            document.add(createTable());
            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            throw new ServiceException("Some problems with create Pdf", e);
        }
    }

    private PdfPTable createTable() throws IOException {
        Map<UserDTO, List<ReportDTO>> map;
        map = JSONParser.getJSON();
        table = new PdfPTable(NUMS_COLUMNS);
        table.setHorizontalAlignment(10);
        table.setSpacingBefore(20);
        table.setWidthPercentage(100);
        createHeader();
        createBody(map);
        return table;
    }

    private void createHeader() {
        Stream.of(HEADERS_NAMES)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(2);
                    header.setVerticalAlignment(1);
                    header.setHorizontalAlignment(1);
                    header.setPadding(15);
                    header.setPhrase(new Phrase(columnTitle, HEADER_FONT));
                    table.addCell(header);
                });
    }

    private void createBody(Map<UserDTO, List<ReportDTO>> map) {
        for (UserDTO user : map.keySet()) {
            PdfPCell userNameCell = new PdfPCell();
            userNameCell.setColspan(3);
            userNameCell.setBorderWidth(2);
            userNameCell.setPadding(15);
            userNameCell.setBackgroundColor(BaseColor.YELLOW);
            userNameCell.setPhrase(new Phrase(user.getFirstName() + " " + user.getLastName(), USER_NAME_FONT));
            table.addCell(userNameCell);
            List<ReportDTO> reports = map.get(user);
            fillCellsForCurrentReport(reports);
        }
    }

    private void fillCellsForCurrentReport(List<ReportDTO> reports) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(2);
        cell.setPadding(15);
        for (ReportDTO reportDTO : reports) {
            cell.setPhrase(new Phrase(reportDTO.getReportTitle(), COLS_FONT));
            table.addCell(cell);
            cell.setPhrase(new Phrase(reportDTO.getReportBody(), COLS_FONT));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(reportDTO.getLaborCost()), COLS_FONT));
            table.addCell(cell);
        }
    }

}

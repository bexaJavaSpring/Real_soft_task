package com.example.real_soft_task.controller;

import com.example.real_soft_task.model.History;
import com.example.real_soft_task.repository_service.HistoryService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public String downloadFile(HttpServletResponse response) {
        try {
            File actions = new File("src/main/resources/actions.pdf");
            FileOutputStream fileOutputStream = null;

            fileOutputStream = new FileOutputStream(actions);
            PdfWriter pdfWriter = new PdfWriter(fileOutputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            pdfDocument.addNewPage();
            Paragraph paragraph = new Paragraph("Recent Actions");
            paragraph.setBold();
            paragraph.setFontSize(22);
            paragraph.setTextAlignment(TextAlignment.CENTER);

            document.add(paragraph);

            Table table = new Table(6);

            table.addCell("Id ");
            table.addCell("user id");
            table.addCell("created at");
            table.addCell("action type");
            table.addCell("object type");
            table.addCell("object name");

            List<History> historyList = historyService.findAll();

            for (History history : historyList) {
                table.addCell(history.getId() + "");
                table.addCell(history.getUser_id() + "");
                table.addCell(history.getCreated_at() + "");
                table.addCell(history.getAction() + "");
                table.addCell(history.getObject() + "");
                table.addCell(history.getObject_name() + "");
            }

            document.add(table);
            document.close();
            fileOutputStream.close();


            response.setHeader("Content-Disposition",
                    "attachment; filename = \""
                            + actions.getName() + "\"");

            response.setContentType(Files.probeContentType(actions.toPath()));// nima bo'lasa shuni ayatadi

            FileCopyUtils.copy(Files.readAllBytes(actions.toPath()), response.getOutputStream());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "adminPage";
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

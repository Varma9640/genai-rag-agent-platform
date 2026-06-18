package com.varma.domain.service;

import com.varma.model.DocumentUploadRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class PdfService {
    public String parseDocument(DocumentUploadRequest request) {

        MultipartFile file = request.getFile();
        try (PDDocument document = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            log.info("PDF Text Extracted Successfully");
            log.info(text);
            return text;

        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse PDF", ex);
        }
    }
}

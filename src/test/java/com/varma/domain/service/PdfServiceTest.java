package com.varma.domain.service;

import com.varma.model.DocumentUploadRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PdfServiceTest {
    private final PdfService service = new PdfService();

    @Test
    void testParseDocumentSuccess() throws Exception {

        PDDocument pdfDocument = new PDDocument();
        pdfDocument.addPage(new PDPage());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfDocument.save(outputStream);
        pdfDocument.close();

        MockMultipartFile file = new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        outputStream.toByteArray());

        DocumentUploadRequest request = new DocumentUploadRequest();
        request.setFile(file);
        String result = service.parseDocument(request);
        assertNotNull(result);
    }

    @Test
    void testParseDocumentException() {

        MockMultipartFile file = new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "invalid pdf".getBytes());

        DocumentUploadRequest request = new DocumentUploadRequest();
        request.setFile(file);
        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> service.parseDocument(request));
        assertEquals("Failed to parse PDF", exception.getMessage());
    }
}

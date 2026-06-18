package com.varma.document;

import com.varma.model.DocumentUploadRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentProcessor {
    private final PdfParserService pdfParserService;

    public String processDocument(DocumentUploadRequest request) {
        log.info("Processing document : {}", request.getFile().getOriginalFilename());
        return pdfParserService.parseDocument(request);
    }
}

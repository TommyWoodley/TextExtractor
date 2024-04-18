package com.example.ocrprocessor.controller;

import com.example.ocrprocessor.service.ContentExtractionFactory;
import com.example.ocrprocessor.service.extractors.ContentExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class OcrController {

    @Autowired
    private ContentExtractionFactory contentExtractionFactory;

    @PostMapping("/extracted-content")
    public ResponseEntity<String> getExtractedContent(@RequestParam("image") MultipartFile file) throws IOException {
        String fileType = file.getContentType();

        try {
            ContentExtractor extractor = contentExtractionFactory.getExtractor(file.getContentType());
            String content = extractor.extractContent(file);
            return ResponseEntity.ok(content);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body("Unsupported file type: " + fileType);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error extracted content from file.");
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Health: OK");
    }

}
